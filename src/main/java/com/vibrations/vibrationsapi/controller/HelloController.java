package com.vibrations.vibrationsapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller -- Test RESTAPI Controller for Compiling Spring Boot
 * Should be deleted when we have actual Models and Controllers
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index() {
        return "S'all Good, Man (Better Call Saul!)";
    }
}
