package com.youssef.jpatsk.dto;

import com.youssef.jpatsk.entity.Author;
import com.youssef.jpatsk.entity.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    CourseDto courseToCourseDto(Course course);

    Course courseDtoToCourse(CourseDto dto);

    List<CourseDto> coursesToCoursesDto(List<Course> courses);

    AuthorDto authorToAuthorDto(Author author);

}
