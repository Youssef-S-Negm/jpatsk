package com.youssef.jpatsk.controller;

import com.youssef.jpatsk.dto.AuthorDto;
import com.youssef.jpatsk.exception.EntityNotFoundException;
import com.youssef.jpatsk.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
public class AuthorControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService authorService;

    @Test
    public void getAuthorByEmail_withExistingEmail_returnsAuthorDto() throws Exception {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);
        authorDto.setName("Alice");
        authorDto.setEmail("alice@gmail.com");
        authorDto.setBirthDate(new Date());

        when(authorService.findByEmail("alice@gmail.com"))
                .thenReturn(authorDto);

        mockMvc.perform(get("/authors")
                        .param("email", "alice@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    public void getAuthorByEmail_withNonExistingEmail_returnsErrorResponse() throws Exception {
        when(authorService.findByEmail("alice@gmail.com"))
                .thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/authors")
                        .param("email", "alice@gmail.com"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }
}
