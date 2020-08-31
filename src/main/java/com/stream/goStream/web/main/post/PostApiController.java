package com.stream.goStream.web.main.post;

import com.stream.goStream.domain.post.dto.PostGetResponseDto;
import com.stream.goStream.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private PostService postService;




}
