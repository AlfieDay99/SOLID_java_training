package com.taskmaster.core;

/**
 * Concrete implementation of Task for bug tracking.
 * Demonstrates:
 * - LSP: Can be used anywhere a Task is expected
 * - SRP: Focused only on bug-specific behavior
 */
public class BugTask extends Task {
    private String stepsToReproduce;
    private String expectedBehavior;
    private String actualBehavior;

    public BugTask(String id, String description, TaskPriority priority,
                  String stepsToReproduce, String expectedBehavior, String actualBehavior) {
        super(id, description, priority);
        this.stepsToReproduce = stepsToReproduce;
        this.expectedBehavior = expectedBehavior;
        this.actualBehavior = actualBehavior;
    }

    @Override
    public String getTaskType() {
        return "BUG";
    }

    // Bug-specific getters and setters
    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void setStepsToReproduce(String stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }

    public String getExpectedBehavior() {
        return expectedBehavior;
    }

    public void setExpectedBehavior(String expectedBehavior) {
        this.expectedBehavior = expectedBehavior;
    }

    public String getActualBehavior() {
        return actualBehavior;
    }

    public void setActualBehavior(String actualBehavior) {
        this.actualBehavior = actualBehavior;
    }
} 