package com.project.springtask.Controller;

import com.project.springtask.Entity.Users;
import com.project.springtask.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getAssigned/{id}")
    public ResponseEntity<Long> getAssigned(@PathVariable Long id)
    {
        Optional<Users> oUsers = userService.getAssigned(id);
        if(oUsers.isPresent())
        {
            return ResponseEntity.ok(oUsers.get().getId());
        }
        return ResponseEntity.status(400).body(null);
    }

    @PostMapping
    public ResponseEntity<String> postUser(@RequestBody Users user)
    {
        Optional<Users> nUser = this.userService.PostUser(user);

        //If valid send back username(not the password)
        if(nUser.isPresent())
        {
            return ResponseEntity.ok(nUser.get().getUsername());
        }
        //If the nUser is null send back error 409
        return ResponseEntity.status(409).body("Username already exists");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delUserId(@PathVariable Long id)
    {
        Optional<Users> nUser = this.userService.delUser(id);
        if(nUser.isPresent())
        {
            return ResponseEntity.ok(nUser.get().getUsername( ) + " was deleted successfully");
        }
        return ResponseEntity.status(400).body("Not a valid id");
    }

    @DeleteMapping("/by-username/{username}")
    public ResponseEntity<String> delUsersByName(@PathVariable String username)
    {
        Optional<Users> nUser = this.userService.delUser(username);
        if(nUser.isPresent())
        {
            return ResponseEntity.ok(nUser.get().getUsername() + " was deleted successfully");
        }
        return ResponseEntity.status(400).body("Not a valid username");
    }
}
