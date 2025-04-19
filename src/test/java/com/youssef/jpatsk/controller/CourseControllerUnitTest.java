package com.youssef.jpatsk.controller;

import com.youssef.jpatsk.dto.CourseDto;
import com.youssef.jpatsk.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
public class CourseControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseService courseService;

    @Test
    public void getCourses_withValidPageAndLimit_returnsCourseDtoList() throws Exception {
        CourseDto dto1 = new CourseDto();
        dto1.setId(1L);
        dto1.setName("Java Basics");
        dto1.setDescription("Introduction to Java programming");
        dto1.setCredit(3);

        CourseDto dto2 = new CourseDto();
        dto2.setId(1L);
        dto2.setName("Java Basics");
        dto2.setDescription("Introduction to Java programming");
        dto2.setCredit(3);

        when(courseService.findAll(0, 2))
                .thenReturn(List.of(dto1, dto2));

        mockMvc.perform(get("/courses")
                        .param("page", "0")
                        .param("limit", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Java Basics"))
                .andExpect(jsonPath("$[1].name").value("Java Basics"));
    }

    @Test
    public void getCourses_withInvalidPageAndLimit_returnsEmptyList() throws Exception {
        when(courseService.findAll(0, 1))
                .thenReturn(List.of());

        mockMvc.perform(get("/courses")
                .param("page", "0")
                .param("limit", "1"))
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
