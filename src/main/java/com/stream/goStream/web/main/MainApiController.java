package com.stream.goStream.web.main;

import com.stream.goStream.domain.post.dto.PostSaveRequestDto;
import com.stream.goStream.service.file.FileService;
import com.stream.goStream.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class MainApiController {

    private final PostService postService;

    private final HttpSession httpSession;

    @PostMapping("/api/uploadPost")
    public Long save(@ModelAttribute PostSaveRequestDto requestDto) {
        return postService.savePost(requestDto);
    }


}
