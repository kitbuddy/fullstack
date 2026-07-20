package com.fullstack.demo.service;

import com.fullstack.demo.dao.TodoDAO;
import com.fullstack.demo.model.Todo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoDAO todoDAO;

    public TodoServiceImpl(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDAO.getAllTodos();
    }

    @Override
    public Todo getTodoById(Integer id) {
        return todoDAO.getTodoById(id);
    }
}
