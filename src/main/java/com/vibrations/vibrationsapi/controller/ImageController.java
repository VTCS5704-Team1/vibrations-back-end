package com.vibrations.vibrationsapi.controller;

import com.vibrations.vibrationsapi.exception.ValidationException;
import com.vibrations.vibrationsapi.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private S3Service s3Service;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadPFP(@RequestPart(value="pfp") final MultipartFile mpFile) {
        try {
            return ResponseEntity.ok(s3Service.uploadFile(mpFile));
        } catch (IOException ex) {
            throw new ValidationException(ex.getMessage());
        }
    }
}
