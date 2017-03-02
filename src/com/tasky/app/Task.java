package com.tasky.app;

/**
 * Created by markus on 2017-02-28.
 */
public class Task {
    private String name;
    private Boolean completed;

    Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
