package com.project.springtask.Config;

import com.project.springtask.Entity.Task;
import com.project.springtask.Entity.Users;
import com.project.springtask.Repository.TaskRepo;
import com.project.springtask.Repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class TestingConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepo userRepo, TaskRepo taskRepo) {
        return args -> {
            // Create tasks
            Task t1 = new Task("Task 1", "Complete module 1", 1, LocalDate.now().plusDays(3), false);
            Task t2 = new Task("Task 2", "Submit the report", 2, LocalDate.now().plusDays(5), false);
            Task t3 = new Task("Task 3", "Prepare presentation", 3, LocalDate.now().plusDays(7), false);

            // Create users
            ArrayList<Task> tasks1 = new ArrayList<>();
            tasks1.add(t1);
            tasks1.add(t2);

            Users u1 = new Users("math123", "1234", tasks1, false);

            ArrayList<Task> tasks2 = new ArrayList<>();
            tasks2.add(t3);

            Users u2 = new Users("sarah123", "2345", tasks2, true);

            // Link tasks to users
            t1.setUsers(u1);
            t2.setUsers(u1);
            t3.setUsers(u2);

            Users u3 = new Users("Joc123", "6789", new ArrayList<>(), false);

            // Save users (cascade will save tasks)
            userRepo.saveAll(List.of(u1, u2, u3));
        };
    }
}
