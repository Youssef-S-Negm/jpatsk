package com.youssef.jpatsk.controller;

import com.youssef.jpatsk.dto.CourseDto;
import com.youssef.jpatsk.dto.ErrorResponse;
import com.youssef.jpatsk.service.CourseService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of courses",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CourseDto.class)))
                    }
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid page or limit format",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    })
    })
    @GetMapping
    public List<CourseDto> getCourses(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "2") int limit) {
        return courseService.findAll(page, limit);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Add a course",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CourseDto.class))
                    }
            )
    })
    @PostMapping
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto course) {
        return new ResponseEntity<>(courseService.add(course), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update course by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CourseDto.class)
                            )}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Provided course id doesn't exist",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    })
    })
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id,
                                                  @RequestBody CourseDto course) {
        return ResponseEntity.ok(courseService.update(id, course));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Delete course by ID"
            ),
            @ApiResponse(responseCode = "404",
                    description = "Provided course id doesn't exist",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDto> deleteCourseById(@PathVariable Long id) {
        courseService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
