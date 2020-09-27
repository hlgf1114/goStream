package com.stream.goStream.web.main.post;

import com.stream.goStream.domain.member.Member;
import com.stream.goStream.domain.member.dto.MemberGetResponseDto;
import com.stream.goStream.domain.post.dto.PostGetResponseDto;
import com.stream.goStream.domain.post.dto.PostSaveRequestDto;
import com.stream.goStream.domain.post.dto.PostUpdateRequestDto;
import com.stream.goStream.service.member.MemberService;
import com.stream.goStream.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;
    private final MemberService memberService;


    @PostMapping("/api/post")
    public Long save(@ModelAttribute PostSaveRequestDto requestDto) {
        Member member = memberService.findMemberEntityById(requestDto.getUploader());
        return postService.savePost(requestDto, member);
    }

    @DeleteMapping("/api/post/{postId}")
    public void delete(@PathVariable(name = "postId") Long postId){

        postService.deletePost(postId);

    }
    
    @PutMapping("/api/post/{postId}")
    public Long update(@ModelAttribute PostUpdateRequestDto requestDto) {
        return postService.updatePost(requestDto);

    }



}
