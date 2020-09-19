package com.stream.goStream.web.main.post;

import com.stream.goStream.domain.post.dto.PostGetResponseDto;
import com.stream.goStream.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/postlist")
    public String getPosts(Model model) {

        List<PostGetResponseDto> posts = postService.getAllPosts();

        if(posts != null)
            model.addAttribute("postList", posts);

        return "post/post_list";
    }

    @GetMapping("/post/{postId}")
    public String getOnePost(Model model, @PathVariable(name = "postId") Long postId) {

        PostGetResponseDto dto = postService.getPost(postId);

        model.addAttribute("post", dto);

        return "post/post_detail";

    }

    @GetMapping("/post/update/{postId}")
    public String getUpdate(Model model, @PathVariable(name = "postId") Long postId) {

        PostGetResponseDto dto = postService.getPost(postId);

        model.addAttribute("post", dto);

        return "post/post_update";
    }

}
