package com.project.springtask.Service;

import com.project.springtask.Entity.Task;
import com.project.springtask.Entity.Users;
import com.project.springtask.Repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    public TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Optional<List<Task>> getAllTask()
    {
        return Optional.of(taskRepo.findAll());
    }

    public Optional<Task> getTaskById(Long id)
    {
        return taskRepo.findById(id);
    }

    public Optional<Users> getUserFromTask(Long id)
    {
        Optional<Task> task = Optional.of(taskRepo.findById(id).get());
        return Optional.of(task.get().getUsers());
    }
}
