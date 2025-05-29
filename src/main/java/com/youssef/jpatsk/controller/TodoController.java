package com.youssef.jpatsk.controller;

import com.youssef.jpatsk.entity.Todo;
import com.youssef.jpatsk.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.addTodo(todo), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.update(id, todo));
    }
}
