package com.project.springtask.Service;

import com.project.springtask.Entity.Task;
import com.project.springtask.Entity.Users;
import com.project.springtask.Repository.TaskRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Optional<Task> postNewTask(Task newTask)
    {
        return Optional.of(taskRepo.save(newTask));
    }

    public Optional<Task> delTask(Long id)
    {
        Optional<Task> oTask = taskRepo.findById(id);
        if(oTask.isPresent())
        {
            this.taskRepo.deleteById(oTask.get().getId());
            return oTask;
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<Task> putTask(Long id, String title, String description, Integer priority, LocalDate dueDate, Boolean completed)
    {
        Optional<Task> oTask = taskRepo.findById(id);
        if(oTask.isPresent())
        {
            Task task = oTask.get();
            if(title != null && !title.isEmpty() && !title.equals(task.getTitle()))
            {
                task.setTitle(title);
            }
            if(description != null && !description.isEmpty() && !description.equals(task.getDescription()))
            {
                task.setDescription(description);
            }
            if (priority != null && priority != task.getPriority())
            {
                task.setPriority(priority);
            }
            if(dueDate != null && dueDate != task.getDueDate())
            {
                task.setDueDate(dueDate);
            }
            if(completed != null && completed != task.isCompleted())
            {
                task.setCompleted(completed);
            }

            this.taskRepo.save(task);
            return Optional.of(task);
        }
        return Optional.empty();
    }
}
