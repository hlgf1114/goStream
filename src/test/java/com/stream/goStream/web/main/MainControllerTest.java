package com.stream.goStream.web.main;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void 메인페이지_진입() throws Exception {

        String content = "인덱스";

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(content));

    }

}
