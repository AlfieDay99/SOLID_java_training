package com.taskmaster.ui;

import com.taskmaster.core.Task;
import com.taskmaster.core.TaskStatus;
import com.taskmaster.core.TaskPriority;
import com.taskmaster.service.TaskService;
import com.taskmaster.service.TaskNotFoundException;
import com.taskmaster.util.TaskFactory;

import java.util.List;
import java.util.Scanner;

/**
 * Console-based user interface for task management.
 * Demonstrates:
 * - DIP: Depends on TaskService abstraction
 * - SRP: Focused only on user interface logic
 */
public class ConsoleUI {
    private final TaskService taskService;
    private final Scanner scanner;

    public ConsoleUI(TaskService taskService) {
        this.taskService = taskService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1 -> createTask();
                    case 2 -> listTasks();
                    case 3 -> completeTask();
                    case 4 -> deleteTask();
                    case 5 -> filterTasksByStatus();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (TaskNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n=== TaskMaster Menu ===");
        System.out.println("1. Create Task");
        System.out.println("2. List All Tasks");
        System.out.println("3. Complete Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Filter Tasks by Status");
        System.out.println("0. Exit");
    }

    private void createTask() {
        System.out.println("\n=== Create New Task ===");
        String description = getStringInput("Enter task description: ");
        String type = getStringInput("Enter task type (BUG/FEATURE): ");
        
        // Get priority
        System.out.println("\nSelect priority:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");
        int priorityChoice = getIntInput("Enter priority choice: ");
        
        TaskPriority priority = switch (priorityChoice) {
            case 1 -> TaskPriority.LOW;
            case 2 -> TaskPriority.MEDIUM;
            case 3 -> TaskPriority.HIGH;
            default -> {
                System.out.println("Invalid choice. Using MEDIUM priority.");
                yield TaskPriority.MEDIUM;
            }
        };
        
        try {
            // Use factory to create task
            Task task = TaskFactory.createTask(type, description, priority);
            taskService.createTask(task);
            System.out.println("\nTask created successfully!");
            displayTask(task);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listTasks() {
        System.out.println("\n=== All Tasks ===");
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            tasks.forEach(this::displayTask);
        }
    }

    private void completeTask() throws TaskNotFoundException {
        System.out.println("\n=== Complete Task ===");
        String id = getStringInput("Enter task ID to complete: ");
        Task task = taskService.completeTask(id);
        System.out.println("Task completed successfully!");
        displayTask(task);
    }

    private void deleteTask() throws TaskNotFoundException {
        System.out.println("\n=== Delete Task ===");
        String id = getStringInput("Enter task ID to delete: ");
        taskService.deleteTask(id);
        System.out.println("Task deleted successfully!");
    }

    private void filterTasksByStatus() {
        System.out.println("\n=== Filter Tasks by Status ===");
        System.out.println("1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");
        int statusChoice = getIntInput("Enter status choice: ");
        
        TaskStatus status = switch (statusChoice) {
            case 1 -> TaskStatus.PENDING;
            case 2 -> TaskStatus.IN_PROGRESS;
            case 3 -> TaskStatus.COMPLETED;
            default -> {
                System.out.println("Invalid choice. Showing all tasks.");
                yield null;
            }
        };

        List<Task> tasks = status != null ? 
            taskService.getTasksByStatus(status) : 
            taskService.getAllTasks();
            
        if (tasks.isEmpty()) {
            System.out.println("No tasks found with the selected status.");
        } else {
            tasks.forEach(this::displayTask);
        }
    }

    private void displayTask(Task task) {
        System.out.println("\nTask ID: " + task.getId());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Status: " + task.getStatus());
        System.out.println("Priority: " + task.getPriority());
        System.out.println("Type: " + task.getTaskType());
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
} 