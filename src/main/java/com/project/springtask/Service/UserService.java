package com.project.springtask.Service;

import com.project.springtask.Entity.Task;
import com.project.springtask.Entity.Users;
import com.project.springtask.Repository.TaskRepo;
import com.project.springtask.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public UserRepo userRepo;
    public TaskRepo taskRepo;

    public UserService(UserRepo userRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    //Implements all the service needed//

    public Optional<List<Users>> getAllUser() {
        return Optional.of(userRepo.findAll());
    }

    public Optional<List<Users>> getAllEmployees() {
        return Optional.of(userRepo.findAll().stream().filter(u -> !u.isManager()).toList());
    }

    public Optional<List<Users>> getAllManagers() {
        return Optional.of(userRepo.findAll().stream().filter(Users::isManager).toList());
    }

    public Optional<Users> getById(Long id) {
        return userRepo.findById(id);   //FindById already return a optional
    }

    public Optional<Users> PostUser(Users users)
    {
        //Validate if username password already exist
        Optional<Users> nUser = userRepo.findUsersByUsername(users.getUsername());
        //if already exist send back null
        if(nUser.isPresent())
        {
            return Optional.empty();
        }
        //if not sava and send back the user
        this.userRepo.save(users);
        return Optional.of(users);
    }

    public Optional<Users> delUser(Long id)
    {
        Optional<Users> nUser = this.userRepo.findById(id);
        //If nUser exist delete and send back the username
        if(nUser.isPresent())
        {
            this.userRepo.deleteById(nUser.get().getId());
            return nUser;
        }
        //If not send back null
        return Optional.empty();
    }

    public Optional<Users> delUser(String username)
    {
        Optional<Users> nUser = this.userRepo.findUsersByUsername(username);
        if(nUser.isPresent())
        {
            this.userRepo.deleteById(nUser.get().getId());
            return nUser;
        }
        return Optional.empty();
    }

    public Optional<Users> getAssigned(Long id)
    {
        Optional<Task> oTask = this.taskRepo.findById(id);
        if(oTask.isPresent())
        {
            return Optional.of(oTask.get().getUsers());
        }
        return Optional.empty();
    }
}
