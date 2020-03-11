package com.corpbay.application.api.controllers;

import com.corpbay.application.api.models.Users;
import com.corpbay.application.api.services.MailService;
import com.corpbay.application.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {

    private final UserService userService;

    private final MailService mailService;

    @Autowired
    public UserController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody Users user) {
         try {
             Users registeredUser = userService.addUser(user);
             boolean response = mailService.registrationMail(registeredUser);
             return ResponseEntity.ok(registeredUser);
         } catch (Exception ex) {
             System.out.println(ex.getMessage());
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

//    @PostMapping("/login")
//    public ResponseEntity<Object> loginUser(@RequestBody Users user) {
//        try {
//            return ResponseEntity.ok(userService.checkUserLogin(user));
//        } catch(Exception ex) {
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
        try {
            return ResponseEntity.ok().body(userService.getUserByEmail(email));
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("verify")
    public ResponseEntity<Object> verifyUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            URLDecoder.decode(password,"UTF-8");
            Users user = userService.getUserByEmailAndPassword(email, password);
            if(user.getVerified())
                return ResponseEntity.ok("User Verified Successfully! Please login on your app");
            else
                return ResponseEntity.ok("Some undefined error occur please try after sometime");
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
