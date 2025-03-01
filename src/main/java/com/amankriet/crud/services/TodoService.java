package com.amankriet.crud.services;

import com.amankriet.crud.exception.ResourceNotFoundException;
import com.amankriet.crud.models.Todo;
import com.amankriet.crud.repositories.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

    private final TodoRepository todoRepository;

    public TodoService(@Autowired TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        logger.info("Fetching all todos");
        List<Todo> todos = todoRepository.findAll();
        logger.debug("Number of todos: {}", todos.size());
        return todos;
    }

    public Todo getTodoById(String id) {
        logger.info("Fetching todo with id: {}", id);
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TODO not found with id: " + id));
    }

    public Todo createTodo(Todo todo) {
        logger.info("Creating new todo with task: {}", todo.getTask());
        Todo createdTodo = todoRepository.save(todo);
        logger.debug("Created todo: {}", createdTodo);
        return createdTodo;
    }

    public Todo updateTodo(Todo todoDetails) {
        logger.info("Updating todo with id: {}", todoDetails.getId());
        Todo updatedTodo = todoRepository.save(todoDetails);
        logger.debug("Updated todo: {}", updatedTodo);
        return updatedTodo;
    }

    public void deleteTodo(String id) {
        logger.info("Deleting todo with id: {}", id);
        todoRepository.deleteById(id);
        logger.debug("Deleted todo with id: {}", id);
    }
}
