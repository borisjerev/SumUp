package com.borisjerev.sumUp.controller;

import com.borisjerev.sumUp.model.TasksRequest;
import com.borisjerev.sumUp.service.api.OrderTasksServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = ProcessingController.class)
public class ProcessingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderTasksServiceInterface orderTaskService;

    @Test
    public void no_tasks_bad_request_exception() throws Exception {
        TasksRequest tasksRequest = new TasksRequest();
        this.mockMvc.perform(get("/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(tasksRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void empty_tasks_bad_request_exception() throws Exception {
        TasksRequest tasksRequest = new TasksRequest();
        tasksRequest.setTasks(new ArrayList<>());
        this.mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(tasksRequest)))
                .andExpect(status().isBadRequest());
    }
}
