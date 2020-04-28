package com.corpbay.application.api.controllers;

import com.corpbay.application.api.services.LoggedInDeviceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class LoggedInDeviceController {
    private final LoggedInDeviceServices loggedInDeviceServices;

    @Autowired
    public LoggedInDeviceController(LoggedInDeviceServices loggedInDeviceServices) {
        this.loggedInDeviceServices = loggedInDeviceServices;
    }

    @GetMapping
    public ResponseEntity<Object> getAllDevices() {
        return ResponseEntity.ok(loggedInDeviceServices.getAllDevices());
    }
}
