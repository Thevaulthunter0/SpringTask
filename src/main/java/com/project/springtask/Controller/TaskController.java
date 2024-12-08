package com.project.springtask.Controller;

import com.project.springtask.Service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/task")
public class TaskController {

    public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

}
