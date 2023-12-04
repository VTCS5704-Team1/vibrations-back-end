package com.vibrations.vibrationsapi.service;

import com.amazonaws.services.cognitoidp.model.AdminDeleteUserResult;
import com.amazonaws.services.cognitoidp.model.AdminSetUserPasswordResult;
import com.amazonaws.services.cognitoidp.model.GlobalSignOutResult;
import com.vibrations.vibrationsapi.dto.*;
import com.vibrations.vibrationsapi.model.ProfileImage;
import com.vibrations.vibrationsapi.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

/**
 * Service functions for User model
 */
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

    /**
     * Registers a user into the database
     * @param registerRequest -- User information
     * @return Confirmation JSON
     * @throws IOException -- Excetion
     */
    RegisterResponseDto register(RegisterRequestDto registerRequest) throws IOException;

    /**
     * Gets a specific user
     * @param downloadUserRequestDto -- User email
     * @return -- User information JSON
     */
    DownloadUserResponseDto getUser(DownloadUserRequestDto downloadUserRequestDto);

    /**
     * Returns a list of all Users in the database
     * @return All users in the database
     */
    List<User> getAllUsers();

    /**
     * Returns of list of all PFPs in the database
     * @return All PFps in the database
     */
    public  List<ProfileImage> getAllPfp();

    /**
     * Get Matches generated by Lambda matchmaking algorithm
     *
     * @return Matches for signed in user
     */
    LambdaMatchmakingResponse getMatches(String email);
}



