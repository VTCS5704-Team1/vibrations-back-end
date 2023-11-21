package com.vibrations.vibrationsapi.service.impl;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.vibrations.vibrationsapi.dto.*;
import com.vibrations.vibrationsapi.exception.ValidationException;
import com.vibrations.vibrationsapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    @Value(value="${aws.cognito.clientId}")
    private String clientId;

    @Value(value = "${aws.cognito.userPoolId}")
    private String userPoolId;
    @Override
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequest) {
        SignUpResponseDto signUpResponse = new SignUpResponseDto();
        try {
            // Get attributes (email)
            AttributeType emailAttr = new AttributeType().withName("email").withValue(signUpRequest.getEmail());
            AttributeType emailVerifiedAttr = new AttributeType().withName("email_verified").withValue("true");
            // Get Other Attributes
            AttributeType birthdateAttr = new AttributeType().withName("birthdate").withValue(signUpRequest.getBirthdate());
            AttributeType genderAttr = new AttributeType().withName("gender").withValue(signUpRequest.getGender());

            // Create user request to Cognito
            AdminCreateUserRequest userRequest = new AdminCreateUserRequest().withUserPoolId(userPoolId)
                    .withUsername(signUpRequest.getEmail()).withTemporaryPassword(signUpRequest.getPassword())
                    .withUserAttributes(emailAttr, emailVerifiedAttr, birthdateAttr, genderAttr)
                    .withMessageAction(MessageActionType.SUPPRESS)
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

            // Submit request to the client
            AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(userRequest);

            System.out.println("User " + createUserResult.getUser().getUsername()
                    + " is created. Status: " + createUserResult.getUser().getUserStatus());

            // Disable force change password during login
            AdminSetUserPasswordRequest adminSetUserPasswordRequest = new AdminSetUserPasswordRequest()
                    .withUsername(signUpRequest.getEmail()).withUserPoolId(userPoolId)
                    .withPassword(signUpRequest.getPassword()).withPermanent(true);

            cognitoClient.adminSetUserPassword(adminSetUserPasswordRequest);
            signUpResponse.setStatusCode(0);
            signUpResponse.setStatusMessage("Successfully created user account.");
        } catch (Exception e) {
            throw new ValidationException("Error during signup: " + e.getMessage());
        }
        return signUpResponse;
    }


    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequest) {
        SignInResponseDto signInResponse = new SignInResponseDto();

        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", signInRequest.getEmail());
        authParams.put("PASSWORD", signInRequest.getPassword());

        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId(clientId)
                .withUserPoolId(userPoolId).withAuthParameters(authParams);

        System.out.println("Trying to sign in...");
        try {
            AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);

            AuthenticationResultType authenticationResult = null;

            if (result.getChallengeName() != null && !result.getChallengeName().isBlank()) {
                System.out.println("Challenge name is " + result.getChallengeName());

                if (result.getChallengeName().contentEquals("NEW_PASSWORD_REQUIRED")) {
                    if (signInRequest.getPassword() == null) {
                        throw new ValidationException("User must change password "  + result.getChallengeName());
                    } else {
                        final Map<String, String> challengeResponses = new HashMap<>();
                        challengeResponses.put("USERNAME", signInRequest.getEmail());
                        challengeResponses.put("PASSWORD", signInRequest.getPassword());
                        // add new password
                        challengeResponses.put("NEW_PASSWORD", signInRequest.getNewPassword());

                        final AdminRespondToAuthChallengeRequest request =
                                new AdminRespondToAuthChallengeRequest()
                                        .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                                        .withChallengeResponses(challengeResponses).withClientId(clientId)
                                        .withUserPoolId(userPoolId).withSession(result.getSession());

                        AdminRespondToAuthChallengeResult resultChallenge =
                                cognitoClient.adminRespondToAuthChallenge(request);
                        authenticationResult = resultChallenge.getAuthenticationResult();

                        signInResponse.setAccessToken(authenticationResult.getAccessToken());
                        signInResponse.setIdToken(authenticationResult.getIdToken());
                        signInResponse.setRefreshToken(authenticationResult.getRefreshToken());
                        signInResponse.setExpiresIn(authenticationResult.getExpiresIn());
                        signInResponse.setTokenType(authenticationResult.getTokenType());
                    }
                } else {
                    throw new ValidationException("User has other challenge "  + result.getChallengeName());
                }
            } else {
                System.out.println("User has no challenge");
                authenticationResult = result.getAuthenticationResult();

                signInResponse.setAccessToken(authenticationResult.getAccessToken());
                signInResponse.setIdToken(authenticationResult.getIdToken());
                signInResponse.setRefreshToken(authenticationResult.getRefreshToken());
                signInResponse.setExpiresIn(authenticationResult.getExpiresIn());
                signInResponse.setTokenType(authenticationResult.getTokenType());
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
        // cognitoClient.shutdown();
        return signInResponse;
    }

    @Override
    public GlobalSignOutResult signOut(HttpServletRequest signOutRequest) {
        String accessToken = getAccessToken(signOutRequest);
        if (accessToken == null) {
            throw new ValidationException("No access token found!");
        }

        try {
            GlobalSignOutRequest request = new GlobalSignOutRequest();
            request.setAccessToken(accessToken);

            return cognitoClient.globalSignOut(request);

        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public AdminSetUserPasswordResult changePassword(ChangePasswordDto changePasswordRequest) {

        // First, ensure that user entered proper credentials:
        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", changePasswordRequest.getEmail());
        authParams.put("PASSWORD", changePasswordRequest.getOldPassword());

        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withClientId(clientId)
                .withUserPoolId(userPoolId)
                .withAuthParameters(authParams);
        try {
            AdminInitiateAuthResult authResult = cognitoClient.adminInitiateAuth(authRequest);
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }

        try {
            AdminSetUserPasswordRequest request = new AdminSetUserPasswordRequest()
                    .withUserPoolId(userPoolId)
                    .withUsername(changePasswordRequest.getEmail())
                    .withPassword(changePasswordRequest.getNewPassword())
                    .withPermanent(true);
            return cognitoClient.adminSetUserPassword(request);
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public AdminDeleteUserResult deleteUser(DeleteAccountDto deleteUserRequest) {
        try {
            AdminDeleteUserRequest request = new AdminDeleteUserRequest()
                    .withUserPoolId(userPoolId)
                    .withUsername(deleteUserRequest.getEmail());
            return cognitoClient.adminDeleteUser(request);
        } catch (Exception e){
            throw new ValidationException(e.getMessage());
        }
    }


    private String getAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
