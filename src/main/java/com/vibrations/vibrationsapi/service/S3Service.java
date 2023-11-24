package com.vibrations.vibrationsapi.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.vibrations.vibrationsapi.dto.UploadImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {

    /**
     * Request method for uploading a PFP to a S3 Bucket
     * @param mpFile -- File being uploaded
     * @return AWS S3 JSON response to PutObject request
     */
    UploadImageResponseDto uploadFile(MultipartFile mpFile) throws IOException;



}
