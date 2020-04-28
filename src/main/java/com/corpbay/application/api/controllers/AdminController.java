package com.corpbay.application.api.controllers;

import com.corpbay.application.api.entity.Admin;
import com.corpbay.application.api.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminServices adminServices;

    @Autowired
    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @PostMapping
    public ResponseEntity<Object> adminRegistration(@RequestBody Admin admin) {
        try {
            return ResponseEntity.ok(adminServices.addAdmin(admin));
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllAdmin() {
        try {
            return ResponseEntity.ok(adminServices.getAllAdmin());
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> getAdmin(@PathVariable String username) {
        try {
            return ResponseEntity.ok(adminServices.getAdmin(username));
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
