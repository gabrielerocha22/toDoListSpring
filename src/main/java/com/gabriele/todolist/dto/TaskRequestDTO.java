package com.gabriele.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO {
    
    @NotBlank(message = "O título não pode estar vazio.")
    private String title;

    private String description;

    @NotBlank(message = "O status não pode estar vazio.")
    private String status;
}
