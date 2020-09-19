package com.stream.goStream.domain.post.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateRequestDto {

    private String title;
    private String content;
    private Long postId;

    @Builder
    public PostUpdateRequestDto(String title, String content, Long postId) {
        this.title = title;
        this.content = content;
        this.postId = postId;
    }
}
