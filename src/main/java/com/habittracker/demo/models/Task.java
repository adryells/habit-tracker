package com.habittracker.demo.models;

public class Task {
    private int id;
    private String description;
    private boolean isCompleted;

    public Task(int id, String description, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[✔]" : "[ ]") + " " + description + " (ID: " + id + ")";
    }
}