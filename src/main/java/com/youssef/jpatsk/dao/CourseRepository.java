package com.youssef.jpatsk.dao;

import com.youssef.jpatsk.entity.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
}
