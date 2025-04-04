package com.taskmaster.core;

/**
 * Concrete implementation of Task for feature development.
 * Demonstrates:
 * - LSP: Can be used anywhere a Task is expected
 * - SRP: Focused only on feature-specific behavior
 */
public class FeatureTask extends Task {
    private String acceptanceCriteria;
    private String technicalNotes;
    private int estimatedHours;

    public FeatureTask(String id, String description, TaskPriority priority,
                      String acceptanceCriteria, String technicalNotes, int estimatedHours) {
        super(id, description, priority);
        this.acceptanceCriteria = acceptanceCriteria;
        this.technicalNotes = technicalNotes;
        this.estimatedHours = estimatedHours;
    }

    @Override
    public String getTaskType() {
        return "FEATURE";
    }

    // Feature-specific getters and setters
    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getTechnicalNotes() {
        return technicalNotes;
    }

    public void setTechnicalNotes(String technicalNotes) {
        this.technicalNotes = technicalNotes;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }
} 