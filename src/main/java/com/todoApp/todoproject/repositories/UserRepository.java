package com.todoApp.todoproject.repositories;

import com.todoApp.todoproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsernameAndPassword(String username, String password);
    // username과 password DB안에 존재하는 지 판단

    Users findByUsername(String username);
    // username DB안에 존재 판단

    Users findTopByPassword(String password);
    // username과 password가 동일한 레코드인지 판단.
}
