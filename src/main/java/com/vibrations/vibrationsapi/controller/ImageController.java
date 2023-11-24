package com.vibrations.vibrationsapi.controller;

import com.vibrations.vibrationsapi.dto.DownloadImageRequestDto;
import com.vibrations.vibrationsapi.exception.ValidationException;
import com.vibrations.vibrationsapi.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
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

    @GetMapping(value = "/download")
    public ResponseEntity<?> downloadPFP(@RequestBody DownloadImageRequestDto downloadRequest) {
        return ResponseEntity.ok((s3Service.downloadFile(downloadRequest)));
    }
}
