package com.project.springtask.Service;

import com.project.springtask.Repository.TaskRepo;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    public TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    //Implements all the service needed//

}
