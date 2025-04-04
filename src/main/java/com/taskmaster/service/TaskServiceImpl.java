package com.taskmaster.service;

import com.taskmaster.core.Task;
import com.taskmaster.core.TaskStatus;
import com.taskmaster.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of TaskService.
 * Demonstrates:
 * - DIP: Depends on TaskRepository abstraction
 * - SRP: Focused only on business logic implementation
 */
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    /**
     * Constructor with dependency injection
     * @param taskRepository The task repository to use
     */
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTask(String id) throws TaskNotFoundException {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) throws TaskNotFoundException {
        // Verify task exists
        getTask(task.getId());
        return taskRepository.update(task);
    }

    @Override
    public void deleteTask(String id) throws TaskNotFoundException {
        // Verify task exists
        getTask(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Task completeTask(String id) throws TaskNotFoundException {
        Task task = getTask(id);
        task.setStatus(TaskStatus.COMPLETED);
        return taskRepository.update(task);
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }
} 