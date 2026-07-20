package com.fullstack.demo.service;

import com.fullstack.demo.model.Todo;
import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    Todo getTodoById(Integer id);
}
