package com.vibrations.vibrationsapi.security;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AWS S3 settings configuring. Accesses a S3 bucket used to store Profile Picture
 * Images.
 */
@Configuration
public class S3Configuration {

    @Value(value = "${aws.access-key}")
    private String accessKey;

    @Value(value = "${aws.access-secret}")
    private String secretKey;

    @Value(value = "${aws.s3.region}")
    private String region;

    @Bean
    public AmazonS3 s3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder.
                standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
