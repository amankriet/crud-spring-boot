package com.amankriet.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoRequest {
    @NotBlank(message = "Task cannot be empty")
    @Size(min = 1, max = 200, message = "Task length must be between 1 and 200 characters")
    private String task;

    // Default is false, if provided, it will override.
    private boolean completed = false;
}
