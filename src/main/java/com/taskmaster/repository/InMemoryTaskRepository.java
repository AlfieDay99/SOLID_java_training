package com.taskmaster.repository;

import com.taskmaster.core.Task;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * In-memory implementation of TaskRepository using Singleton pattern.
 * Demonstrates:
 * - DIP: Implements the TaskRepository interface
 * - SRP: Focused only on in-memory storage implementation
 * - Singleton Pattern: Ensures only one instance exists
 */
public class InMemoryTaskRepository implements TaskRepository {
    private static InMemoryTaskRepository instance;
    private final ConcurrentMap<String, Task> tasks;

    private InMemoryTaskRepository() {
        this.tasks = new ConcurrentHashMap<>();
    }

    /**
     * Get the singleton instance of the repository
     * @return The singleton instance
     */
    public static synchronized InMemoryTaskRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryTaskRepository();
        }
        return instance;
    }

    @Override
    public Task save(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Optional<Task> findById(String id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> findAll() {
        return List.copyOf(tasks.values());
    }

    @Override
    public boolean deleteById(String id) {
        return tasks.remove(id) != null;
    }

    @Override
    public Task update(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return task;
        }
        throw new IllegalArgumentException("Task not found with id: " + task.getId());
    }
} 