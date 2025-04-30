package com.youssef.jpatsk.controller;

import com.youssef.jpatsk.dto.AuthorDto;
import com.youssef.jpatsk.dto.DtoMapper;
import com.youssef.jpatsk.dto.ErrorResponse;
import com.youssef.jpatsk.entity.Author;
import com.youssef.jpatsk.service.AuthorService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found author by email",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorDto.class)
                            )}
            ),
            @ApiResponse(responseCode = "404",
                    description = "No author found with specified email",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    })
    })
    @GetMapping
    public AuthorDto getAuthorByEmail(@RequestParam String email) {
        return authorService.findByEmail(email);
    }

}
