package com.todoApp.todoproject.controller;

import com.todoApp.todoproject.entity.Users;
import com.todoApp.todoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// @CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //GET
    @GetMapping("/login")
    private Users getCurrentUser(@RequestBody Users user){
        // @RequestBody : Json형태의 데이터를 자동으로 Users 타입으로 변환해주는 역할을 한다.
        System.out.println("GET User by username and password *****");
        return userService.getUser(user);
    }

    @GetMapping("/login/{username}/{password}")
    private boolean findUserByUsername(@PathVariable String username, @PathVariable String password){
        System.out.println("GET User by username and password *****");
        return userService.getUserByUsername(username, password);
    }

    //POST
    @PostMapping("/createUser")
    private boolean addUser(@RequestBody Users user) {
        boolean user_exits = userService.findUserByUsername(user.getUsername());
        if(user_exits) {
            System.out.println("CANT CREATE USER!");
            return false;
        }
        userService.saveUser(user);
        return true;
    }
}
