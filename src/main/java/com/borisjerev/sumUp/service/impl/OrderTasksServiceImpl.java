package com.borisjerev.sumUp.service.impl;

import com.borisjerev.sumUp.model.TaskRequest;
import com.borisjerev.sumUp.model.TaskResponse;
import com.borisjerev.sumUp.service.api.OrderTasksServiceInterface;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderTasksServiceImpl implements OrderTasksServiceInterface {
    @Override
    public List<TaskResponse> orderTasks(List<TaskRequest> tasks) {
        List<TaskResponse> orderedTasks = new ArrayList<>();
        implementOrder(tasks, new ArrayList<>(), orderedTasks);
        return orderedTasks;
    }

    @Override
    public String bashScriptRepresentation(List<TaskRequest> tasks) {
        List<TaskResponse> orderedTasks = new ArrayList<>();
        implementOrder(tasks, new ArrayList<>(), orderedTasks);

        StringBuilder builder = new StringBuilder();
        builder.append("#!/usr/bin/env bash\n\n");

        orderedTasks.forEach(t -> { builder.append(t.getCommand()); builder.append("\n"); } );

        return builder.toString();
    }

    private void implementOrder(List<TaskRequest> tasks, List<String> dependsOn, List<TaskResponse> orderedTasks) {
        Iterator<TaskRequest> iterator = tasks.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        while(iterator.hasNext()) {
            TaskRequest taskRequest = iterator.next();
            if (areDependenciesSatisfied(taskRequest.getRequires(), dependsOn)) {
                dependsOn.add(taskRequest.getName());
                orderedTasks.add(new TaskResponse(taskRequest.getName(), taskRequest.getCommand()));
                iterator.remove();
                break;
            }
        }

        implementOrder(tasks, dependsOn, orderedTasks) ;
    }

    private boolean areDependenciesSatisfied(List<String> requires, List<String> alreadyOrdered) {
        if (requires == null ) {
            requires = new ArrayList<>();
        }
        for (String require : requires) {
            Optional<String> required = alreadyOrdered.stream().filter(s -> s.equals(require) ).findFirst();
            if (!required.isPresent()) {
                return false;
            }
        }

        return true;
    }
}
