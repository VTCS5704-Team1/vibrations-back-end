package com.vibrations.vibrationsapi.controller;

import com.vibrations.vibrationsapi.exception.ValidationException;
import com.vibrations.vibrationsapi.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * API Controller for handling image uploads
 */
@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private S3Service s3Service;

    /**
     * Uploads a PFP object to the database
     * @param mpFile -- The file being uploaded
     * @param email -- Email of the user that the pfp belongs to
     * @return -- JSON confirmation
     */
    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadPFP(
            @RequestPart(value="pfp") final MultipartFile mpFile,
            @RequestPart(value="email") String email) {
        try {
            return ResponseEntity.ok(s3Service.uploadFile(mpFile , email));
        } catch (IOException ex) {
            throw new ValidationException(ex.getMessage());
        }
    }
}
