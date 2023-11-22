package com.vibrations.vibrationsapi.repository;

import com.vibrations.vibrationsapi.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    Optional<ProfileImage> findByName(String name);
}
