package com.youssef.jpatsk.service;

import com.youssef.jpatsk.dao.CourseRepository;
import com.youssef.jpatsk.dto.CourseDto;
import com.youssef.jpatsk.dto.DtoMapper;
import com.youssef.jpatsk.entity.Course;
import com.youssef.jpatsk.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public CourseDto add(CourseDto course) {
        Course courseEntity = mapper.dtoToCourse(course);

        return mapper.courseToDto(courseRepository.save(courseEntity));
    }

    @Override
    public CourseDto update(Long id, CourseDto course) {
        Course courseEntity = mapper.dtoToCourse(course);
        courseEntity.setId(id);

        Optional<Course> result = courseRepository.findById(id);

        if (result.isEmpty())
            throw new EntityNotFoundException("Course id - " + id + " not found");

        return mapper.courseToDto(courseRepository.save(courseEntity));
    }

    @Override
    public void delete(Long id) {
        Optional<Course> result = courseRepository.findById(id);

        if (result.isEmpty())
            throw new EntityNotFoundException("Course id - " + id + " not found");

        courseRepository.deleteById(id);
    }
}
