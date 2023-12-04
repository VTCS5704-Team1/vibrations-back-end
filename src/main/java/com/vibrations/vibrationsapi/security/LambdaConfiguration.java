package com.vibrations.vibrationsapi.security;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AWS Lambda settings configuration. Used for calling the matchmaking algorithm
 * stored in Lambda.
 */
@Configuration
public class LambdaConfiguration {

    @Value(value = "${aws.access-key}")
    private String accessKey;

    @Value(value = "${aws.access-secret}")
    private String secretKey;

    @Value(value = "${aws.s3.region}")
    private String region;


    @Bean
    public AWSLambda lambdaClient() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AWSLambdaClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region)
                .build();
    }
}