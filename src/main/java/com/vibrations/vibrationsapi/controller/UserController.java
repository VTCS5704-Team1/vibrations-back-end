package com.vibrations.vibrationsapi.controller;


import com.vibrations.vibrationsapi.dto.*;
import com.vibrations.vibrationsapi.model.ProfileImage;
import com.vibrations.vibrationsapi.model.User;
import com.vibrations.vibrationsapi.service.S3Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.vibrations.vibrationsapi.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * REST API Controller for Users.
 * Manages both Cognito endpoints and User model endpoints
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private S3Service s3Service;

    /**
     * Registers a User in Cognito
     * @param signUpRequest -- Cognito required User information
     * @return -- Response JSON
     */
    @PostMapping(path="/register", consumes = {MediaType.APPLICATION_JSON_VALUE} ,
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequest) {
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

    /**
     * Logs a User into Cognito
     * @param signInRequest -- email and password
     * @return -- JSON response
     */
    @PostMapping(path="/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequest) {
        return ResponseEntity.ok(userService.signIn(signInRequest));
    }

    /**
     * Changes a User's password in Cognito
     * @param changePasswordRequest -- Email, old password, new password
     * @return -- JSON Response
     */
    @PostMapping(path="/change/password", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordRequest) {
        return ResponseEntity.ok(userService.changePassword(changePasswordRequest));
    }

    /**
     *
     * @param deleteAccountRequest -- Email, password
     * @return -- AWS JSON confirmation
     */
    @PostMapping(path="/delete", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteAccount(@RequestBody DeleteAccountDto deleteAccountRequest) {
        return ResponseEntity.ok(userService.deleteUser(deleteAccountRequest));
    }

    /**
     * Logout request
     * @param request -- Request from frontend -- Only need Bearer Token
     * @return -- Confirmation of signing out (AWS JSON)
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        return ResponseEntity.ok(userService.signOut(request));
    }


    /**
     * Registers User as a data model in the database
     * @param registerRequest -- User information DTO request
     * @return -- Confirmation that user was registered JSON
     */
    @PostMapping(path="/registerUser")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequestDto registerRequest
    ) {
        try {
            System.out.println("response");
            return ResponseEntity.ok(userService.register(registerRequest));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the information of a single user object based off their email
     * @param email -- String of email passed as a request param
     * @return -- User information JSON
     */
    @GetMapping(path="/getUser")
    public ResponseEntity<?> register(@RequestParam("email") String email ) {
        DownloadUserRequestDto downloadUserRequestDto = new DownloadUserRequestDto(email);
        return ResponseEntity.ok(userService.getUser(downloadUserRequestDto));
    }

    /**
     * Returns all Users in the database
     * @return -- All Users in the database (JSON)
     */
    @GetMapping(path="/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Runs the matchmaking algorithm and returns matches for a specified user
     * @param email -- User who's receiving matches (identified by email)
     * @return -- List of matches (JSON)
     */
    @GetMapping(path = "/match")
    public ResponseEntity<?> match(@RequestParam String email) {
        return ResponseEntity.ok(userService.getMatches(email));
    }

    /**
     * Returns all PFPs in the database
     * @return  -- List of PfPs (JSON)
     */
    @GetMapping(path="/allPfp")
    public ResponseEntity<?> getAllPfp() {
        List<ProfileImage> profileImages = userService.getAllPfp();
        List<AllProfileImageDto> allImages = new ArrayList<>();
        for (ProfileImage profileImage : profileImages) {
            DownloadImageRequestDto downloadRequest = new DownloadImageRequestDto();
            downloadRequest.setFileName(profileImage.getName());
            DownloadImageResponseDto imageResponseDto = s3Service.downloadFile(downloadRequest);
            AllProfileImageDto allProfileImageDto = new AllProfileImageDto();
            allProfileImageDto.setImageData(imageResponseDto.getImageData());
            allProfileImageDto.setName(profileImage.getName());
            allProfileImageDto.setEmail(profileImage.getEmail());
            allImages.add(allProfileImageDto);
            System.out.println(allProfileImageDto.getEmail());
        }
        return ResponseEntity.ok(allImages);
    }
}
