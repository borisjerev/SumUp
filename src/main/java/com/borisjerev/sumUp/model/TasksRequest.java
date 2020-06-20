package com.borisjerev.sumUp.model;

import java.util.List;

public class TasksRequest {
    private List<TaskRequest> tasks;

    public List<TaskRequest> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskRequest> tasks) {
        this.tasks = tasks;
    }
}
