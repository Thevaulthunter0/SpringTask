package com.project.springtask.Controller;

import com.project.springtask.Entity.Task;
import com.project.springtask.Entity.Users;
import com.project.springtask.Service.TaskService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping
    public ResponseEntity<Optional<Task>> postTask(@RequestBody Task newTask)
    {
        Optional<Task> nTask = this.taskService.postNewTask(newTask);
        if(nTask.isPresent())
        {
            return ResponseEntity.ok(nTask);
        }
        return ResponseEntity.status(409).body(nTask);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Optional<Task>> delTask(@PathVariable Long id)
    {
        Optional<Task> oTask = this.taskService.delTask(id);
        if(oTask.isPresent())
        {
            return ResponseEntity.ok(oTask);
        }
        HttpHeaders header = new HttpHeaders();
        header.set("Error", "Not a valid id");
        return new ResponseEntity<>(null,header, HttpStatusCode.valueOf(400));
    }

    @PutMapping("{id}")
    public ResponseEntity<Optional<Task>> putTask(@PathVariable Long id,
                                                  @RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String description,
                                                  @RequestParam(required = false) Integer priority,
                                                  @RequestParam(required = false) LocalDate dueDate,
                                                  @RequestParam(required = false) Boolean completed )
    {
        Optional<Task> oTask = this.taskService.putTask(id,title,description,priority,dueDate,completed);
        if(oTask.isPresent())
        {
            return ResponseEntity.ok(oTask);
        }
        HttpHeaders header = new HttpHeaders();
        header.set("Error", "Not a valid id");
        return new ResponseEntity<>(null,header, HttpStatusCode.valueOf(400));
    }
}
