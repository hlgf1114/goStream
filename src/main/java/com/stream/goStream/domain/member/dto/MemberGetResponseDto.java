package com.stream.goStream.domain.member.dto;

import com.stream.goStream.domain.member.Role;
import com.stream.goStream.domain.post.Post;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class MemberGetResponseDto {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;

    private List<Post> postList = new ArrayList<>();

    @Builder
    public MemberGetResponseDto(Long id, String name, String email, String picture, Role role, List<Post> postList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.postList = postList;
    }
}
