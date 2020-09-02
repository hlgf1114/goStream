package com.stream.goStream.web.main;


import com.stream.goStream.config.auth.dto.SessionMember;
import com.stream.goStream.domain.post.dto.PostGetResponseDto;
import com.stream.goStream.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final HttpSession httpSession;
    private final PostService postService;



    @GetMapping("/")
    public String index(Model model) {

        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if(member != null)
            model.addAttribute("memberName", member.getName());

        return "index";
    }

    @GetMapping("/write")
    public String writePost(Model model) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        model.addAttribute("member", member);

        return "write";

    }

}
