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
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;

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

    @Transactional
    public Long savePost(PostSaveRequestDto requestDto) {

        Member member = memberService.findMember(requestDto.getUploader());

        // 파일 저장 부분
        MultipartFile upload = requestDto.getFile();
        String filePath = null;
        try {
            filePath = ManageFile.saveFile(upload);

            // 썸네일도 저장한다.
            String thumbnailPath = filePath.substring(0, filePath.lastIndexOf("."));
            thumbnailPath += ".png";
            java.io.File dest = new java.io.File(thumbnailPath);

            ManageFile.getThumbnail(new java.io.File(filePath), dest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = File.builder()
                .originalFileName(upload.getOriginalFilename())
                .fileSize(upload.getSize())
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
