package com.youssef.jpatsk.repository;

import com.youssef.jpatsk.dao.CourseRepository;
import com.youssef.jpatsk.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
class CourseRepositoryIntegrationTest {

    @Container
    static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.36")
            .withDatabaseName("courses_app")
            .withUsername("root")
            .withPassword("admin");


    private final CourseRepository repository;

    @Autowired
    public CourseRepositoryIntegrationTest(CourseRepository repository) {
        this.repository = repository;
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driverClassName", MY_SQL_CONTAINER::getDriverClassName);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
        registry.add("spring.jpa.hibernate.naming.physical-strategy",
                () -> "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
    }

    @BeforeEach
    public void setup() {
        Course course1 = new Course(1L, "Java Basics", "Introduction to Java programming.", 3);
        Course course2 = new Course(2L, "Kotlin", "Intro to Kotlin.", 4);
        Course course3 = new Course(3L, "Python", "Intro to python", 5);
        List<Course> courses = List.of(course1, course2, course3);

        MY_SQL_CONTAINER.start();

        repository.saveAll(courses);
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
