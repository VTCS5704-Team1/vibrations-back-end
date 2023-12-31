package com.vibrations.vibrationsapi.repository;

import com.vibrations.vibrationsapi.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JpaRepository for storing/retrieving Profile Images from database.
 */
@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    Optional<ProfileImage> findByName(String name);
    ProfileImage findByEmail(String email);


}
