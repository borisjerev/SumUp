package com.borisjerev.sumUp.service;

import com.borisjerev.sumUp.model.TaskRequest;
import com.borisjerev.sumUp.model.TaskResponse;
import com.borisjerev.sumUp.model.TasksRequest;
import com.borisjerev.sumUp.service.api.OrderTasksServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTaskServiceTest {

    @Autowired
    private OrderTasksServiceInterface orderTaskService;

    @Test
    public void testOrderTasks() {
        TasksRequest tasksRequest = new TasksRequest();
        tasksRequest.setTasks(new ArrayList<>());

        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("task-1");
        taskRequest.setCommand("touch /tmp/file1");
        taskRequest.setRequires(null);

        tasksRequest.getTasks().add(taskRequest);

        taskRequest = new TaskRequest();
        taskRequest.setName("task-2");
        taskRequest.setCommand("cat /tmp/file1");
        taskRequest.setRequires(Arrays. asList("task-3"));

        tasksRequest.getTasks().add(taskRequest);

        taskRequest = new TaskRequest();
        taskRequest.setName("task-3");
        taskRequest.setCommand("echo 'Hello World!' > /tmp/file1");
        taskRequest.setRequires(Arrays.asList("task-1"));

        tasksRequest.getTasks().add(taskRequest);

        taskRequest = new TaskRequest();
        taskRequest.setName("task-4");
        taskRequest.setCommand("rm /tmp/file1");
        taskRequest.setRequires(Arrays.asList("task-2", "task-3"));

        tasksRequest.getTasks().add(taskRequest);

        List<TaskResponse> taskResponse = orderTaskService.orderTasks(tasksRequest.getTasks());

        assertEquals(taskResponse.size(), 4);
        assertEquals(taskResponse.get(0).getName(), "task-1");
        assertEquals(taskResponse.get(1).getName(), "task-3");
        assertEquals(taskResponse.get(2).getName(), "task-2");
        assertEquals(taskResponse.get(3).getName(), "task-4");
    }
}
