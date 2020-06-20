package com.borisjerev.sumUp.controller;

import com.borisjerev.sumUp.exception.BadTasksRequestException;
import com.borisjerev.sumUp.model.TaskResponse;
import com.borisjerev.sumUp.model.TasksRequest;
import com.borisjerev.sumUp.service.api.OrderTasksServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProcessingController {
    @Autowired
    private OrderTasksServiceInterface orderTaskService;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<TaskResponse>> orderTasks(@RequestBody TasksRequest tasks) {
        if (tasks.getTasks() == null || tasks.getTasks().isEmpty()) {
            throw new BadTasksRequestException("No tasks provided");
        }
        return new ResponseEntity<>(orderTaskService.orderTasks(tasks.getTasks()), HttpStatus.OK);
    }

    @RequestMapping(value = "/bashScript", method = RequestMethod.GET)
    public ResponseEntity<String> bashScript(@RequestBody TasksRequest tasks) {
        if (tasks.getTasks() == null || tasks.getTasks().isEmpty()) {
            throw new BadTasksRequestException("No tasks provided");
        }
        String bashScriptRepresentation = orderTaskService.bashScriptRepresentation(tasks.getTasks());
        return new ResponseEntity<>(bashScriptRepresentation, HttpStatus.OK);
    }
}
