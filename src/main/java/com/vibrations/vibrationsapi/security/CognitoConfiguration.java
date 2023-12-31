package com.vibrations.vibrationsapi.security;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AWS Cognito settings Configuration. Used for accessing Cognito User pool
 * for registration, logging in, logging out, changing password, and deleting account.
 */
@Configuration
public class CognitoConfiguration {
    @Value(value = "${aws.access-key}")
    private String accessKey;

    @Value(value = "${aws.access-secret}")
    private String secretKey;

    @Bean
    public AWSCognitoIdentityProvider cognitoClient() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion("us-east-2")
                .build();
    }
}
