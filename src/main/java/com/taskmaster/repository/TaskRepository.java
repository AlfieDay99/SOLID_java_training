package com.taskmaster.repository;

import com.taskmaster.core.Task;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for task storage operations.
 * Demonstrates:
 * - DIP: High-level modules depend on this abstraction
 * - SRP: Focused only on data access operations
 */
public interface TaskRepository {
    /**
     * Save a task to the repository
     * @param task The task to save
     * @return The saved task
     */
    Task save(Task task);

    /**
     * Find a task by its ID
     * @param id The task ID
     * @return Optional containing the task if found
     */
    Optional<Task> findById(String id);

    /**
     * Get all tasks
     * @return List of all tasks
     */
    List<Task> findAll();

    /**
     * Delete a task by its ID
     * @param id The task ID
     * @return true if task was deleted, false otherwise
     */
    boolean deleteById(String id);

    /**
     * Update an existing task
     * @param task The task to update
     * @return The updated task
     */
    Task update(Task task);
} 