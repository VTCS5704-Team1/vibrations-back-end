package com.vibrations.vibrationsapi.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibrations.vibrationsapi.dto.HelloDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Just a Test unit Test controller, used for testing how mockMVC works
 */
@SpringBootTest
@AutoConfigureMockMvc()
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHelloGet() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World!")));
    }

    @Test
    void testHelloPost() throws Exception {
        HelloDao dao = new HelloDao();
        dao.setName("David");
        String jsonDao = new ObjectMapper().writeValueAsString(dao);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/hello/friend")
                .contentType("application/json")
                .content(jsonDao)
                .accept("application/json");
        this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, David!")));
    }
}
