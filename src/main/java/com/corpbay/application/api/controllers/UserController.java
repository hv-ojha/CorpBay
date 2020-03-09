package com.corpbay.application.api.controllers;

import com.corpbay.application.api.models.Users;
import com.corpbay.application.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> addUser(@RequestBody Users user) {
         try {
             return ResponseEntity.ok(userService.addUser(user));
         } catch (Exception ex) {
             return ResponseEntity.badRequest().body(ex.getMessage());
         }
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
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
    public ResponseEntity<Object> loginUser(@RequestBody Users user) {
        try {
            return ResponseEntity.ok(userService.checkUserLogin(user));
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
        try {
            return ResponseEntity.ok().body(userService.getUserByEmail(email));
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
