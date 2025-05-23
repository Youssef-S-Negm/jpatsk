package com.youssef.jpatsk.service;

import com.youssef.jpatsk.dao.CourseRepository;
import com.youssef.jpatsk.dto.CourseDto;
import com.youssef.jpatsk.dto.DtoMapper;
import com.youssef.jpatsk.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final DtoMapper mapper;

    public CourseServiceImpl(CourseRepository courseRepository, DtoMapper mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CourseDto> findAll(int page, int limit) {
        Page<Course> coursePage = courseRepository.findAll(PageRequest.of(page, limit));

        return mapper.coursesToCoursesDto(coursePage.stream().toList());
    }
}
