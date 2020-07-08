package com.tcrprojectubs.controller;

import com.tcrprojectubs.domain.User;
import com.tcrprojectubs.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/list")
    public Iterable<User> list() {

        return userService.list();
    }

}
