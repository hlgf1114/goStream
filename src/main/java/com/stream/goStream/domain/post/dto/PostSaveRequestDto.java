package com.stream.goStream.domain.post.dto;

import com.stream.goStream.domain.file.File;
import com.stream.goStream.domain.member.Member;
import com.stream.goStream.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String title;
    private String content;
    private Member member;
    private Long uploader;
    private MultipartFile file;

    @Builder
    public PostSaveRequestDto(String title, String content,
                              Member member, Long uploader,
                              MultipartFile file) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.uploader = uploader;
        this.file = file;
    }


    public Post toEntity() {

        return Post.builder()
                .member(member)
                .content(content)
                .title(title)
                .file(null)
                .build();
    }

}
