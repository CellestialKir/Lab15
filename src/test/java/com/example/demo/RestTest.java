package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestTest {
    @Autowired
    private AppUserRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void cleanDb() {
        repository.deleteAll();
    }

    @Test
    public void testAuthentication() throws Exception {
        this.mockMvc.perform(post("/register")
                        .content("{\"username\":\"TestUser\",\"password\":\"TestPassword\"}")
                        .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                .andDo(print())
                .andExpect(status().isOk());


        this.mockMvc
                .perform(post("/login")
                        .content("{\"username\":\"TestUser\",\"password\":\"TestPassword\"}")
                        .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}