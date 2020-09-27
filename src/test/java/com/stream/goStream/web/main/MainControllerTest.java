package com.stream.goStream.web.main;


import com.stream.goStream.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MainController.class)
@ContextConfiguration(classes = SecurityConfig.class)
public class MainControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mvc;

    @Test
    public void 메인페이지_진입() throws Exception {

        mvc.perform(get("/"))
                .andExpect(status().isOk());

    }

    @Test
    public void 포스트리스트_진입() throws Exception {

        mvc.perform(get("/post")).andExpect(status().isOk());
    }

}
