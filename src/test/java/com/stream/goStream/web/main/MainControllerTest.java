package com.stream.goStream.web.main;


import com.stream.goStream.config.auth.SecurityConfig;
import com.stream.goStream.domain.member.Role;
import com.stream.goStream.service.post.PostService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MainController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                        SecurityConfig.class,
                        PostService.class
                })
        })
public class MainControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Test
    @WithMockUser
    public void 메인페이지_진입() throws Exception {

        mvc.perform(get("/"))
                .andExpect(status().isOk());

    }

    @Test
    public void 포스트리스트_진입() throws Exception {

        mvc.perform(get("/post")).andExpect(status().isOk());
    }

}
