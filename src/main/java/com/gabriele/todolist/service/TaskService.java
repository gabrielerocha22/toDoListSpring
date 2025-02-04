package com.gabriele.todolist.service;

import com.gabriele.todolist.dto.TaskRequestDTO;
import com.gabriele.todolist.dto.TaskResponseDTO;
import com.gabriele.todolist.model.Task;
import com.gabriele.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(this::convertToDto).toList();
    }

    public Optional<TaskResponseDTO> getTaskById(Long id) {
        return taskRepository.findById(id).map(this::convertToDto);
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());
        return convertToDto(taskRepository.save(task));
    }

    public Optional<TaskResponseDTO> updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setTitle(taskRequestDTO.getTitle());
            existingTask.setDescription(taskRequestDTO.getDescription());
            existingTask.setStatus(taskRequestDTO.getStatus());
            return convertToDto(taskRepository.save(existingTask));
        });
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskResponseDTO convertToDto(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setCreatedAt(task.getCreatedAt());
        return dto;
    }
}