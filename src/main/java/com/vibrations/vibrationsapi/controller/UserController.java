package com.vibrations.vibrationsapi.controller;


import com.amazonaws.Response;
import com.vibrations.vibrationsapi.dto.SignInRequestDto;
import com.vibrations.vibrationsapi.dto.SignInResponseDto;
import com.vibrations.vibrationsapi.dto.SignUpRequestDto;
import com.vibrations.vibrationsapi.dto.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.vibrations.vibrationsapi.model.User;
import com.vibrations.vibrationsapi.service.UserService;

import java.util.Arrays;


// TODO: Ensure that when a new user is registered, that user data is transferred to the SQL database
//  (Aside from the password)
@RestController
@RequestMapping("/api/users")
// DEV's NOTE: This is configured for LOCAL DEVELOPMENT
// When deploying to Cloud, this will need to be updated
@CrossOrigin(origins="http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/register", consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequest) {
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

    @PostMapping(path="/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequest) {
        return ResponseEntity.ok(userService.signIn(signInRequest));
    }

    // Temp GET method for testing endpoints only accessible via Authentication
    @GetMapping(path="/data")
    public ResponseEntity<?> data() {
        return ResponseEntity.ok(Arrays.asList("S'all Good, Man!"));
    }

    /*@GetMapping("/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    } */
}
