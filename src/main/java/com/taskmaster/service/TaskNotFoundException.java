package com.taskmaster.service;

/**
 * Exception thrown when a task is not found in the repository.
 */
public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
} 