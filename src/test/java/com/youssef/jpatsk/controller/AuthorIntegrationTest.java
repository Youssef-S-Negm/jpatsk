package com.youssef.jpatsk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAuthorByEmail_withExistingEmail_returnsAuthorDto() throws Exception {
        mockMvc.perform(get("/authors")
                        .param("email", "alice@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    public void getAuthorByEmail_withNonExistingEmail_returnsErrorResponse() throws Exception {
        mockMvc.perform(get("/authors")
                        .param("email", "alice@gmail.com"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }
}
