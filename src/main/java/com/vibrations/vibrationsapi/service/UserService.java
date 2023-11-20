package com.vibrations.vibrationsapi.service;

import com.vibrations.vibrationsapi.dto.*;
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
}
