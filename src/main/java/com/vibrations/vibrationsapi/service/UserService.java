package com.vibrations.vibrationsapi.service;

import com.amazonaws.services.cognitoidp.model.AdminDeleteUserResult;
import com.amazonaws.services.cognitoidp.model.AdminSetUserPasswordResult;
import com.amazonaws.services.cognitoidp.model.GlobalSignOutResult;
import com.vibrations.vibrationsapi.dto.*;
import com.vibrations.vibrationsapi.model.ProfileImage;
import com.vibrations.vibrationsapi.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {
    /**
     * Method for registering an account using AWS Cognito
     *
     * @param signUpRequest -- POST RequestBody for signing up
     * @return Response to signup request
     */
    SignUpResponseDto signUp(SignUpRequestDto signUpRequest);

    /**
     * Method for signing in AWS Cognito User Pool
     *
     * @param signInRequest -- POST RequestBody for signing in
     * @return Response to sign in request
     */
    SignInResponseDto signIn(SignInRequestDto signInRequest);

    /**
     * Method for signing out in AWS Cognito
     *
     * @param signOutRequest -- Request for signing out
     * @return AWS Response to Sign Out Request
     */
    GlobalSignOutResult signOut(HttpServletRequest signOutRequest);

    /**
     * Method for changing passwords in Cognito.
     *
     * @param changePasswordRequest -- New Password DTO (and email)
     * @return AWS JSON Response to Change Password Request
     */
    AdminSetUserPasswordResult changePassword(ChangePasswordDto changePasswordRequest);

    /**
     * Method for deleting an account in Cognito
     *
     * @param deleteUserRequest -- Email of using to be deleted
     * @return AWS JSON Response to Delete User request
     */
    AdminDeleteUserResult deleteUser(DeleteAccountDto deleteUserRequest);

    RegisterResponseDto register(RegisterRequestDto registerRequest , MultipartFile file) throws IOException;

    Optional<User> findUserByEmail(String email);

    ProfileImage findProfileByEmail(String email);

    DownloadUserResponseDto getUser(DownloadUserRequestDto downloadUserRequestDto);
}



