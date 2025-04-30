package com.youssef.jpatsk.controller;

import com.youssef.jpatsk.dto.PostDto;
import com.youssef.jpatsk.service.PostService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of courses",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PostDto.class))
                            )}
            )
    })
    @GetMapping
    public List<PostDto> getPosts() {
        return postService.getPosts();
    }
}
