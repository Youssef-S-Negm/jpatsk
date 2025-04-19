package com.youssef.jpatsk.repository;

import com.youssef.jpatsk.dao.CourseRepository;
import com.youssef.jpatsk.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CourseRepositoryIntegrationTest {

    private final CourseRepository repository;

    @Autowired
    public CourseRepositoryIntegrationTest(CourseRepository repository) {
        this.repository = repository;
    }

    @Test
    public void getCourses_withPage0AndLimit3_returnsThreeCourses() {
        List<Course> courses = repository.findAll(PageRequest.of(0,3)).stream().toList();

        assertEquals(3, courses.size());
        assertEquals("Java Basics", courses.getFirst().getName());
    }

    @Test
    public void getCourses_whenPage5AndLimit1returnsEmptyList() {
        List<Course> courses = repository.findAll(PageRequest.of(5,1)).stream().toList();

        assertEquals(0, courses.size());
    }
}
