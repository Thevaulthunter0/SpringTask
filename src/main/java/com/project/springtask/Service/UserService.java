package com.project.springtask.Service;

import com.project.springtask.Entity.Users;
import com.project.springtask.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
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
        return userRepo.findById(id);
    }
}
