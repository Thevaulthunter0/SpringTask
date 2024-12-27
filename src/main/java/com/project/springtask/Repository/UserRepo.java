package com.project.springtask.Repository;

import com.project.springtask.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByUsername(String username);
}
