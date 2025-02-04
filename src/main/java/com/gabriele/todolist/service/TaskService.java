package com.gabriele.todolist.service;

import com.gabriele.todolist.dto.TaskRequestDTO;
import com.gabriele.todolist.dto.TaskResponseDTO;
import com.gabriele.todolist.model.Task;
import com.gabriele.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> new TaskResponseDTO(
                        task.getId(), task.getTitle(), task.getDescription(),
                        task.getStatus(), task.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public Optional<TaskResponseDTO> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> new TaskResponseDTO(
                        task.getId(), task.getTitle(), task.getDescription(),
                        task.getStatus(), task.getCreatedAt()));
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());

        Task savedTask = taskRepository.save(task);

        return new TaskResponseDTO(
                savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(),
                savedTask.getStatus(), savedTask.getCreatedAt());
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
