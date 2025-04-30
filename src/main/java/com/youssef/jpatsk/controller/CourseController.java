package com.youssef.jpatsk.controller;

import com.youssef.jpatsk.dto.CourseDto;
import com.youssef.jpatsk.service.CourseService;
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

    @GetMapping
    public List<CourseDto> getCourses(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "2") int limit) {
        return courseService.findAll(page, limit);
    }

    @PostMapping
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto course) {
        return new ResponseEntity<>(courseService.add(course), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id,
                                                  @RequestBody CourseDto course) {
        return ResponseEntity.ok(courseService.update(id, course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDto> deleteCourseById(@PathVariable Long id) {
        courseService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
