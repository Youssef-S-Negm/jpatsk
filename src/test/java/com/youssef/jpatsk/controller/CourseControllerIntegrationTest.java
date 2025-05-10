package com.youssef.jpatsk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCourses_withValidPageAndLimit_returnsCourseDtoList() throws Exception {
        mockMvc.perform(get("/courses")
                        .param("page", "0")
                        .param("limit", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Java Basics"))
                .andExpect(jsonPath("$[1].name").value("Database Design"));
    }

    @Test
    public void getCourses_withInvalidPageAndLimit_returnsEmptyList() throws Exception {
        mockMvc.perform(get("/courses")
                        .param("page", "1")
                        .param("limit", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getCourses_withInvalidPageAndLimitFormat_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/courses")
                        .param("page", "a")
                        .param("limit", "c"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()));
    }
}
