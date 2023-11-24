//package com.vibrations.vibrationsapi.service;
//
//import com.vibrations.vibrationsapi.model.ProfileImage;
//import com.vibrations.vibrationsapi.repository.ProfileImageRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ProfileImageService {
//
//    @Autowired
//    ProfileImageRepository profileImageRepository;
//
//    public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {
//
//        ProfileImageRepository.save(ProfileImage.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .ProfileImage(ImageUtil.compressImage(file.getBytes())).build());
//
//        return new ImageUploadResponse("Image uploaded successfully: " +
//                file.getOriginalFilename());
//
//    }
//
//    @Transactional
//    public ProfileImage getInfoByImageByName(String name) {
//        Optional<ProfileImage> dbImage = ProfileImageRepository.findByName(name);
//
//        return ProfileImage.builder()
//                .name(dbImage.get().getName())
//                .type(dbImage.get().getType())
//                .ProfileImage(ImageUtil.decompressImage(dbImage.get().getProfileImage())).build();
//
//    }
//
//    @Transactional
//    public byte[] getImage(String name) {
//        Optional<ProfileImage> dbImage = ProfileImageRepository.findByName(name);
//        byte[] image = ImageUtil.decompressImage(dbImage.get().getProfileImage());
//        return image;
//    }
//
//
//
//}
