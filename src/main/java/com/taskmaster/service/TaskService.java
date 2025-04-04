package com.taskmaster.service;

import com.taskmaster.core.Task;
import com.taskmaster.core.TaskStatus;
import java.util.List;

/**
 * Service interface for task management operations.
 * Demonstrates:
 * - DIP: High-level modules depend on this abstraction
 * - SRP: Focused only on business operations
 */
public interface TaskService {
    /**
     * Create a new task
     * @param task The task to create
     * @return The created task
     */
    Task createTask(Task task);

    /**
     * Get a task by its ID
     * @param id The task ID
     * @return The task if found
     * @throws TaskNotFoundException if task not found
     */
    Task getTask(String id) throws TaskNotFoundException;

    /**
     * Get all tasks
     * @return List of all tasks
     */
    List<Task> getAllTasks();

    /**
     * Update a task
     * @param task The task to update
     * @return The updated task
     * @throws TaskNotFoundException if task not found
     */
    Task updateTask(Task task) throws TaskNotFoundException;

    /**
     * Delete a task
     * @param id The task ID
     * @throws TaskNotFoundException if task not found
     */
    void deleteTask(String id) throws TaskNotFoundException;

    /**
     * Mark a task as completed
     * @param id The task ID
     * @return The updated task
     * @throws TaskNotFoundException if task not found
     */
    Task completeTask(String id) throws TaskNotFoundException;

    /**
     * Get tasks by status
     * @param status The status to filter by
     * @return List of tasks with the given status
     */
    List<Task> getTasksByStatus(TaskStatus status);
} 