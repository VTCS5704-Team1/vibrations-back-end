package com.vibrations.vibrationsapi.controller;


import com.vibrations.vibrationsapi.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.vibrations.vibrationsapi.service.UserService;

import java.util.Arrays;


// TODO: Ensure that when a new user is registered, that user data is transferred to the SQL database
//  (Aside from the password)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/signup", consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequest) {
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

    @PostMapping(path="/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequest) {
        return ResponseEntity.ok(userService.signIn(signInRequest));
    }

    @PostMapping(path="/change/password", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordRequest) {
        return ResponseEntity.ok(userService.changePassword(changePasswordRequest));
    }

    @PostMapping(path="/delete", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteAccount(@RequestBody DeleteAccountDto deleteAccountRequest) {
        return ResponseEntity.ok(userService.deleteUser(deleteAccountRequest));
    }

    // Temp GET method for testing endpoints only accessible via Authentication
    @GetMapping(path="/data")
    public ResponseEntity<String> data() {
        return ResponseEntity.ok("S'all Good, Man!");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        return ResponseEntity.ok(userService.signOut(request));
    }

    @GetMapping("/logout/success")
    public ResponseEntity<?> logoutSuccess() {
        return ResponseEntity.ok(Arrays.asList("Logged out Successfully!"));
    }
    @PostMapping(path="/register", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerReques) {
        return ResponseEntity.ok(userService.register(registerReques));
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
