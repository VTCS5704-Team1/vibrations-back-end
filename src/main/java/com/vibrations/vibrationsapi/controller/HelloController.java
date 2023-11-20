package com.vibrations.vibrationsapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller -- Test RESTAPI Controller for Compiling Spring Boot
 * Should be deleted when we have actual Models and Controllers
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Better Call Saul!");
    }
}
