package com.vibrations.vibrationsapi.service;

import com.vibrations.vibrationsapi.exceptions.UserAlreadyExistsException;
import com.vibrations.vibrationsapi.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vibrations.vibrationsapi.repository.UserRepository;
import com.vibrations.vibrationsapi.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());//(user.getEmail());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with the same email already exists.");
        }
        // Save the new user to the database

        return userRepository.save(user);

    }

    public User updateUserProfile(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setProfilePictureLink(updatedUser.getProfilePictureLink());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setFavSong(updatedUser.getFavSong());
            existingUser.setFavArtist(updatedUser.getFavArtist());
            existingUser.setFavGenre(updatedUser.getFavGenre());
            existingUser.setScore(updatedUser.getScore());
            existingUser.setLatitude(updatedUser.getLatitude());
            existingUser.setLongitude(updatedUser.getLongitude());

            // Step 3: Save the updated user to the database
        return userRepository.save(existingUser);
    }
}
