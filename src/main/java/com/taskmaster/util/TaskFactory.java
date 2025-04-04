package com.taskmaster.util;

import com.taskmaster.core.BugTask;
import com.taskmaster.core.FeatureTask;
import com.taskmaster.core.Task;
import com.taskmaster.core.TaskPriority;
import java.util.UUID;

/**
 * Factory class for creating different types of tasks.
 * Demonstrates:
 * - Factory Pattern: Creates objects without exposing instantiation logic
 * - SRP: Focused only on task creation
 */
public class TaskFactory {
    /**
     * Creates a new task based on the specified type.
     * @param type The type of task to create (BUG/FEATURE)
     * @param description The task description
     * @param priority The task priority
     * @return A new task instance
     * @throws IllegalArgumentException if task type is invalid
     */
    public static Task createTask(String type, String description, TaskPriority priority) {
        String id = UUID.randomUUID().toString();
        
        return switch (type.toUpperCase()) {
            case "BUG" -> new BugTask(
                id,
                description,
                priority,
                "Steps to reproduce will be added later",
                "Expected behavior will be added later",
                "Actual behavior will be added later"
            );
            case "FEATURE" -> new FeatureTask(
                id,
                description,
                priority,
                "Acceptance criteria will be added later",
                "Technical notes will be added later",
                0 // Default estimated hours
            );
            default -> throw new IllegalArgumentException("Invalid task type: " + type);
        };
    }
} 