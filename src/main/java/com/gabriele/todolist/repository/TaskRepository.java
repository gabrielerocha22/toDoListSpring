package com.gabriele.todolist.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriele.todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
