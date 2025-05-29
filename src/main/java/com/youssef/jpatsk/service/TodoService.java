package com.youssef.jpatsk.service;

import com.youssef.jpatsk.entity.Todo;

import java.util.List;

public interface TodoService {

    Todo addTodo(Todo todo);

    List<Todo> getTodos();

    Todo update(Long id, Todo todo);

}
