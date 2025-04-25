package com.youssef.jpatsk.repository;

import com.youssef.jpatsk.dao.CourseRepository;
import com.youssef.jpatsk.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void addCourse_returnsCourse() {
        Course addedCourse = repository.save(
                new Course(null, "Test Course", "Test description", 3)
        );

        assertNotNull(addedCourse);
        assertEquals("Test Course", addedCourse.getName());
    }

    @Test
    public void updateCourse_returnsCourse() {
        Course updatedCourse = repository.save(
                new Course(4L, "Test Update Course", "This course will updated", 3)
        );

        assertNotNull(updatedCourse);
        assertEquals("Test Update Course", updatedCourse.getName());
    }

    @Test
    public void deleteCourseById_returnsVoid() {
        repository.deleteById(1L);

        Optional<Course> result = repository.findById(1L);

        assertTrue(result.isEmpty());
    }
}
