package com.taskmaster.core;

/**
 * Abstract base class for all task types.
 * This class demonstrates:
 * - LSP: All task types must be substitutable for Task
 * - SRP: Focused on task properties and basic behavior
 */
public abstract class Task {
    private final String id;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;

    protected Task(String id, String description, TaskPriority priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    // Abstract method that must be implemented by subclasses
    public abstract String getTaskType();
} 