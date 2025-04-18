package com.youssef.jpatsk.controller;

import com.youssef.jpatsk.dto.AuthorDto;
import com.youssef.jpatsk.dto.DtoMapper;
import com.youssef.jpatsk.entity.Author;
import com.youssef.jpatsk.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final DtoMapper mapper;

    public AuthorController(AuthorService authorService, DtoMapper mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @GetMapping
    public AuthorDto getAuthorByEmail(@RequestParam String email) {
        return mapper.authorToAuthorDto(authorService.findByEmail(email));
    }

}
