package com.youssef.jpatsk.controller;

import com.youssef.jpatsk.dto.CourseDto;
import com.youssef.jpatsk.dto.DtoMapper;
import com.youssef.jpatsk.entity.Course;
import com.youssef.jpatsk.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final DtoMapper mapper;

    public CourseController(CourseService courseService, DtoMapper mapper) {
        this.courseService = courseService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CourseDto> getCourses(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "2") int limit) {
        return mapper.coursesToCoursesDto(courseService.findAll(page, limit));
    }
}
