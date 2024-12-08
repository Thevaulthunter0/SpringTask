package com.project.springtask.Repository;

import com.project.springtask.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {
}
