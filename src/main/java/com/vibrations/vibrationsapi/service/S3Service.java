package com.vibrations.vibrationsapi.service;

import com.vibrations.vibrationsapi.dto.DownloadImageRequestDto;
import com.vibrations.vibrationsapi.dto.DownloadImageResponseDto;
import com.vibrations.vibrationsapi.dto.UploadImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service functions for Images (Using Amazon AWS S3)
 */
public interface S3Service {

    /**
     * Request method for uploading a PFP to a S3 Bucket
     * @param mpFile -- File being uploaded
     * @return AWS S3 JSON response to PutObject request
     */
    UploadImageResponseDto uploadFile(MultipartFile mpFile , String uniqueFileName) throws IOException;

    /**
     * Request method for downloading a PFP from a S3 Bucket
     * @param downloadRequest -- Used to identify the file
     * @return -- Response JSON
     */
    DownloadImageResponseDto downloadFile(DownloadImageRequestDto downloadRequest);

}
