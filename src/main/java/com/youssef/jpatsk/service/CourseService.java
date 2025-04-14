package com.youssef.jpatsk.service;

import com.youssef.jpatsk.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> findAll(int page, int limit);

}
