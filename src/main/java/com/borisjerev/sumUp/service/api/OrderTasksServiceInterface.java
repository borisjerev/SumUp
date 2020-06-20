package com.borisjerev.sumUp.service.api;

import com.borisjerev.sumUp.model.TaskRequest;
import com.borisjerev.sumUp.model.TaskResponse;
import com.borisjerev.sumUp.model.TasksRequest;

import java.util.List;

public interface OrderTasksServiceInterface {
    public List<TaskResponse> orderTasks(List<TaskRequest> tasks);
    public String bashScriptRepresentation(List<TaskRequest> tasks);
}
