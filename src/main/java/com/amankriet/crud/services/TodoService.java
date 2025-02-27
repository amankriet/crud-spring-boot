package com.amankriet.crud.services;

import com.amankriet.crud.exception.ResourceNotFoundException;
import com.amankriet.crud.models.Todo;
import com.amankriet.crud.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(String id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TODO not found with id: " + id));
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(String id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("TODO not found with id: " + id));

        todo.setTask(todoDetails.getTask());
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepository.save(todo);
    }

    public void deleteTodo(String id) {
        if (todoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}
