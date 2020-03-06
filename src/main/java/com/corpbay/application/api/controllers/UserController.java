package com.corpbay.application.api.controllers;

import com.corpbay.application.api.models.Users;
import com.corpbay.application.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody Users user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUserById(@PathVariable("id") Long id, @RequestBody Users newUser) {
        userService.updateUserById(id, newUser);
    }

    @PostMapping("/login")
    public Boolean loginUser(@RequestBody Users user) {
        return userService.checkUserLogin(user);
    }

    @GetMapping("/{email}")
    public Users getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }
}
