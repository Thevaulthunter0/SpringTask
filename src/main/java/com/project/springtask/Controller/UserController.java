package com.project.springtask.Controller;

import com.project.springtask.Entity.Users;
import com.project.springtask.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getAll")
    public ResponseEntity<Optional<List<Users>>> getAllUser()
    {
        Optional<List<Users>> optListUsers = userService.getAllUser();
        if(optListUsers.isPresent())
        {
            return ResponseEntity.ok(optListUsers);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("getEmployees")
    public ResponseEntity<Optional<List<Users>>> getAllEmployees()
    {
        Optional<List<Users>> optListUsers = userService.getAllEmployees();
        if(optListUsers.isPresent())
        {
            return ResponseEntity.ok(optListUsers);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("getManagers")
    public ResponseEntity<Optional<List<Users>>> getAllManagers()
    {
        Optional<List<Users>> optListUsers = userService.getAllManagers();
        if(optListUsers.isPresent())
        {
            return ResponseEntity.ok(optListUsers);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Optional<Users>> getUser(@PathVariable Long id)
    {
        Optional<Users> optUser = userService.getById(id);
        if(optUser.isPresent())
        {
            return ResponseEntity.ok(optUser);
        }
        return ResponseEntity.notFound().build();
    }
}
