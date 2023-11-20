package com.vibrations.vibrationsapi.service;

import com.amazonaws.services.cognitoidp.model.GlobalSignOutResult;
import com.vibrations.vibrationsapi.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    /**
     * Method for registering an account using AWS Cognito
     * @param signUpRequest -- POST RequestBody for signing up
     * @return Response to signup request
     */
    SignUpResponseDto signUp(SignUpRequestDto signUpRequest);

    /**
     * Method for signing in AWS Cognito User Pool
     * @param signInRequest -- POST RequestBody for signing in
     * @return Response to sign in request
     */
    SignInResponseDto signIn(SignInRequestDto signInRequest);

    /**
     * Method for signing out in AWS Cognito
     * Should also removing the auth token from the header
     * @param signOutRequest -- Request for signing out
     */
    GlobalSignOutResult signOut(HttpServletRequest signOutRequest);
}
