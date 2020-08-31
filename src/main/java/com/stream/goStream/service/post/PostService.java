package com.stream.goStream.service.post;


import com.stream.goStream.domain.file.File;
import com.stream.goStream.domain.member.Member;
import com.stream.goStream.domain.member.MemberRepository;
import com.stream.goStream.domain.post.Post;
import com.stream.goStream.domain.post.PostRepository;
import com.stream.goStream.domain.post.dto.PostGetResponseDto;
import com.stream.goStream.domain.post.dto.PostSaveRequestDto;
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
    private final MemberRepository memberRepository;

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
                    .filePath(p.getFile().getFilePath())
                    .build();

            result.add(temp);
        }

        return result;
    }

    @Transactional
    public Long savePost(PostSaveRequestDto requestDto) {

        Member member = memberRepository.findById(requestDto.getUploader()).get();


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
