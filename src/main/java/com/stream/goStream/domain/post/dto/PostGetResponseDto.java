package com.stream.goStream.domain.post.dto;

import com.stream.goStream.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostGetResponseDto {

    private String title;
    private String content;
    private Long postId;
    private Long uploader;
    private Long fileId;
    private String uploaderName;

    @Builder
    public PostGetResponseDto(String title, String content, Long postId, Long uploader, Long fileId, String uploaderName) {
        this.title = title;
        this.content = content;
        this.postId = postId;
        this.uploader = uploader;
        this.fileId = fileId;
        this.uploaderName = uploaderName;
    }
}
