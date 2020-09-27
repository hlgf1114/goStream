package com.stream.goStream.service.post;


import com.stream.goStream.domain.file.File;
import com.stream.goStream.domain.member.Member;
import com.stream.goStream.domain.member.MemberRepository;
import com.stream.goStream.domain.member.dto.MemberGetResponseDto;
import com.stream.goStream.domain.post.Post;
import com.stream.goStream.domain.post.PostRepository;
import com.stream.goStream.domain.post.dto.PostGetResponseDto;
import com.stream.goStream.domain.post.dto.PostSaveRequestDto;
import com.stream.goStream.domain.post.dto.PostUpdateRequestDto;
import com.stream.goStream.service.member.MemberService;
import com.stream.goStream.utils.ManageFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;


    @Transactional
    public Long updatePost(PostUpdateRequestDto dto) {

        Long postId = dto.getPostId();
        String title = dto.getTitle();
        String content = dto.getContent();

        Post p = postRepository.findById(postId).get();

        // 업데이트
        p.update(title, content);

        return p.getId();
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public PostGetResponseDto getPost(Long postId) {

        Post post = postRepository.findById(postId).get();

        PostGetResponseDto dto = PostGetResponseDto.builder()
                .content(post.getContent())
                .fileId(post.getFile().getId())
                .postId(post.getId())
                .title(post.getTitle())
                .uploader(post.getMember().getId())
                .build();

        return dto;
    }

    public List<PostGetResponseDto> getAllPosts() {


        List<Post> postsBefore =  postRepository.findAll();

        List<PostGetResponseDto> result = new ArrayList<>();

        for(Post p : postsBefore) {
            PostGetResponseDto temp = PostGetResponseDto
                    .builder()
                    .content(p.getContent())
                    .title(p.getTitle())
                    .uploader(p.getMember().getId())
                    .uploaderName(p.getMember().getName())
                    .postId(p.getId())
                    .fileId(p.getFile().getId())
                    .build();

            result.add(temp);
        }

        return result;
    }

    public Long savePost(PostSaveRequestDto requestDto, Member member) {

        // 파일 저장 부분
        MultipartFile video = requestDto.getFile();
        MultipartFile picture = requestDto.getPicture();

        String filePath = null;
        try {
            // 먼저 비디오와 썸네일을 저장한다.
            if(picture == null || picture.isEmpty())
                filePath = ManageFile.saveFile(video);
            else filePath = ManageFile.saveFile(video, picture);

        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = File.builder()
                .originalFileName(video.getOriginalFilename())
                .fileSize(video.getSize())
                .filePath(filePath)
                .build();

        Post post = Post.builder()
                .file(file)
                .content(requestDto.getContent())
                .member(member)
                .title(requestDto.getTitle())
                .build();

        return postRepository.save(post).getId();
    }

}
