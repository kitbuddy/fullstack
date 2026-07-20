package com.fullstack.demo.dao;

import com.fullstack.demo.model.Todo;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Repository
public class TodoDAOImpl implements TodoDAO {
    private static final String API_URL = "https://jsonplaceholder.typicode.com/todos";
    private final RestTemplate restTemplate;

    public TodoDAOImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Todo> getAllTodos() {
        try {
            Todo[] todos = restTemplate.getForObject(API_URL, Todo[].class);
            return todos != null ? Arrays.asList(todos) : List.of();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching todos from external API", e);
        }
    }

    @Override
    public Todo getTodoById(Integer id) {
        try {
            return restTemplate.getForObject(API_URL + "/" + id, Todo.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching todo with id: " + id, e);
        }
    }
}
