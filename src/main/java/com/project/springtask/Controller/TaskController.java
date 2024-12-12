package com.project.springtask.Controller;

import com.project.springtask.Entity.Task;
import com.project.springtask.Entity.Users;
import com.project.springtask.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/task")
public class TaskController {

    public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("getAll")
    public ResponseEntity<Optional<List<Task>>> getAllTask()
    {
        Optional<List<Task>> oTasks = this.taskService.getAllTask();
        if(oTasks.isPresent())
        {
            return ResponseEntity.ok(oTasks);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable Long id)
    {
        Optional<Task> oTask = this.taskService.getTaskById(id);
        if(oTask.isPresent())
        {
            return ResponseEntity.ok(oTask);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get/{id}user")
    public ResponseEntity<Optional<Users>> GetUserFromTask(@PathVariable Long id)
    {
        Optional<Users> oUser = this.taskService.getUserFromTask(id);
        if(oUser.isPresent())
        {
            return ResponseEntity.ok(oUser);
        }
        return ResponseEntity.notFound().build();
    }
}
