package com.youssef.jpatsk.service;

import com.youssef.jpatsk.dao.TodoRepository;
import com.youssef.jpatsk.entity.Todo;
import com.youssef.jpatsk.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;


    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo update(Long id, Todo todo) {
        Optional<Todo> result = todoRepository.findById(id);

        if (result.isEmpty()) {
            throw new EntityNotFoundException("Todo id - " + id + " is not found");
        }

        todo.setId(id);

        return todoRepository.save(todo);
    }
}
