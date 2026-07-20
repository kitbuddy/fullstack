package com.fullstack.demo.dao;

import com.fullstack.demo.model.Todo;
import java.util.List;

public interface TodoDAO {
    List<Todo> getAllTodos();
    Todo getTodoById(Integer id);
}
