package com.youssef.jpatsk.dao;

import com.youssef.jpatsk.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
