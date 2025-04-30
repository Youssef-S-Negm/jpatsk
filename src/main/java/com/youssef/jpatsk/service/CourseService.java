package com.youssef.jpatsk.service;

import com.youssef.jpatsk.dto.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> findAll(int page, int limit);

}
