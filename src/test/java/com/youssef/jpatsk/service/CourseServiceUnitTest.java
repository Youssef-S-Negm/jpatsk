package com.youssef.jpatsk.service;

import com.youssef.jpatsk.dao.CourseRepository;
import com.youssef.jpatsk.dto.CourseDto;
import com.youssef.jpatsk.dto.DtoMapper;
import com.youssef.jpatsk.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceUnitTest {

    @Mock
    private CourseRepository repository;

    @Mock
    private DtoMapper mapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    public void getCourses_withValidPageAndLimit_returnsCourseDtoList() {
        Course course = new Course(1L, "Java Basics", "Learn Java from scratch", 3);
        CourseDto dto = new CourseDto();
        dto.setId(1L);
        dto.setName("Java Basics");
        dto.setDescription("Learn Java from scratch");
        dto.setCredit(3);

        when(repository.findAll(PageRequest.of(0, 1)))
                .thenReturn(new PageImpl<>(List.of(course)));
        when(mapper.coursesToCoursesDto(List.of(course)))
                .thenReturn(List.of(dto));

        List<CourseDto> result = courseService.findAll(0, 1);

        assertEquals(1, result.size());
        assertEquals("Java Basics", result.getFirst().getName());

        verify(repository).findAll(PageRequest.of(0, 1));
        verify(mapper).coursesToCoursesDto(List.of(course));
    }

    @Test
    public void getCourses_withInvalidPageAndLimit_returnsCourseDtoList() {
        when(repository.findAll(PageRequest.of(1, 1)))
                .thenReturn(new PageImpl<>(List.of()));
        when(mapper.coursesToCoursesDto(List.of()))
                .thenReturn(List.of());

        List<CourseDto> result = courseService.findAll(1, 1);

        assertEquals(0, result.size());

        verify(repository).findAll(PageRequest.of(1, 1));
        verify(mapper).coursesToCoursesDto(List.of());
    }
}
