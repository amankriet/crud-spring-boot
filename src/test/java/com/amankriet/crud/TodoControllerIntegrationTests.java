package com.amankriet.crud;

import com.amankriet.crud.models.Todo;
import com.amankriet.crud.repositories.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
//        Clear the repository before each test
        todoRepository.deleteAll();
    }

    @Test
    public void testGetAllTodos() throws Exception {
//        Arrange: Create sample todos
        Todo todo1 = new Todo(null, "Task 1", false);
        Todo todo2 = new Todo(null, "Task 2", true);
        todoRepository.save(todo1);
        todoRepository.save(todo2);

//        Act & Assert: GET /api/todos
        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].task").value("Task 1"))
                .andExpect(jsonPath("$[0].completed").value(false))
                .andExpect(jsonPath("$[1].task").value("Task 2"))
                .andExpect(jsonPath("$[1].completed").value(true));
    }

    @Test
    public void testGetTodoById() throws Exception {
//        Arrange: Create a sample Todo
        Todo todo = new Todo(null, "Task 1", false);
        todo = todoRepository.save(todo);

//        Act & Assert: GET /api/todos/{id}
        mockMvc.perform(get("/api/todos/" + todo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.task").value("Task 1"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    public void testCreateTodo() throws Exception {
//        Create a new Todo
        Todo todo = new Todo(null, "Task 1", false);

//        Convert TODO to JSON
        String todoJson = objectMapper.writeValueAsString(todo);

//        POST /api/todos to create a new Todo. Creates a new Todo and returns 201 Created
        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.task").value("Task 1"))
                .andExpect(jsonPath("$.completed").value(false));
    }
}
