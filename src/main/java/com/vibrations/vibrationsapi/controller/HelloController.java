package com.vibrations.vibrationsapi.controller;

import com.vibrations.vibrationsapi.dto.HelloDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Just a test controller for testing API functionality
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, World!");
    }

    @PostMapping("/hello/friend")
    public ResponseEntity<String> helloFriend(@RequestBody HelloDao helloRequest) {
        String friendName = helloRequest.getName();
        String message = "Hello, " + friendName + "!";
        return ResponseEntity.ok(message);
    }
}
