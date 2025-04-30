package com.youssef.jpatsk.dto;

import com.youssef.jpatsk.entity.Author;
import com.youssef.jpatsk.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "credit", source = "credit")
    CourseDto courseToCourseDto(Course course);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "credit", source = "credit")
    Course courseDtoToCourse(CourseDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "credit", source = "credit")
    List<CourseDto> coursesToCoursesDto(List<Course> courses);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "birthDate", source = "birthDate")
    AuthorDto authorToAuthorDto(Author author);

}
