package com.youssef.jpatsk.dao;

import com.youssef.jpatsk.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
