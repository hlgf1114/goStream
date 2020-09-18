package com.stream.goStream.web.main.post;

import com.stream.goStream.domain.post.dto.PostGetResponseDto;
import com.stream.goStream.domain.post.dto.PostSaveRequestDto;
import com.stream.goStream.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;


    @PostMapping("/api/post")
    public Long save(@ModelAttribute PostSaveRequestDto requestDto) {
        return postService.savePost(requestDto);
    }

    @DeleteMapping("/api/post/{postId}")
    public void delete(@PathVariable(name = "postId") Long postId){

        postService.deletePost(postId);

    }



}
