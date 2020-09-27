package com.stream.goStream.web.main;

import com.stream.goStream.config.auth.SecurityConfig;
import com.stream.goStream.web.main.post.PostController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostControllerTest {

    private MockMvc mvc;

    @MockBean
    PostController postController;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(postController).build();
    }



    @Test
    public void 포스트리스트_진입() throws Exception {

        mvc.perform(get("/post"))
                .andExpect(status().isOk());

    }


}
