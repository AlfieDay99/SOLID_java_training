package com.taskmaster;

import com.taskmaster.repository.InMemoryTaskRepository;
import com.taskmaster.repository.TaskRepository;
import com.taskmaster.service.TaskService;
import com.taskmaster.service.TaskServiceImpl;
import com.taskmaster.ui.ConsoleUI;

/**
 * Main application class that wires up all components.
 * Demonstrates:
 * - DIP: Components are wired through abstractions
 * - SRP: Main class only responsible for application setup
 */
public class Main {
    public static void main(String[] args) {
        // Create repository (Singleton)
        TaskRepository repository = InMemoryTaskRepository.getInstance();
        
        // Create service with repository dependency
        TaskService taskService = new TaskServiceImpl(repository);
        
        // Create UI with service dependency
        ConsoleUI consoleUI = new ConsoleUI(taskService);
        
        // Start the application
        consoleUI.start();
    }
} 