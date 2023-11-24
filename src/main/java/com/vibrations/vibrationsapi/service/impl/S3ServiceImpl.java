package com.vibrations.vibrationsapi.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.vibrations.vibrationsapi.dto.UploadImageResponseDto;
import com.vibrations.vibrationsapi.exception.ValidationException;
import com.vibrations.vibrationsapi.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class S3ServiceImpl implements S3Service {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Override
    @Async
    public UploadImageResponseDto uploadFile(MultipartFile mpFile) throws IOException {
        try {
            final File file = convertMultipartFileToFile(mpFile);
            final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
            final PutObjectRequest request = new PutObjectRequest(bucketName, uniqueFileName, file);
            s3Client.putObject(request);
            file.delete();

            UploadImageResponseDto response = new UploadImageResponseDto();
            response.setStatusCode(200);
            response.setStatusMessage("Image Successfully Uploaded!");
            response.setFileName(uniqueFileName);

            return response;
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    private File convertMultipartFileToFile(final MultipartFile mpFile) {
        final File file =  new File(mpFile.getOriginalFilename());
        try (final FileOutputStream stream = new FileOutputStream(file)) {
            stream.write(mpFile.getBytes());
        } catch (final IOException ex) {
            throw new ValidationException(ex.getMessage());
        }
        return file;
    }
}
