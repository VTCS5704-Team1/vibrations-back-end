package com.vibrations.vibrationsapi.model;



import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * User data object. Used to store information for Vibrations users.
 */
@Entity
@Data
@Table(name = "users", schema = "vibrations_backend")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "bio")
    private String bio;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private Long phoneNumber;
    @Column(name = "fav_song")
    private List<String> favSong;
    @Column(name = "fav_artist")
    private List<String> favArtist;
    @Column(name = "max_distance")
    private double maxDistance;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "gender")
    private String gender;

    /* Note: private List<String> matches is in the database table, but is used solely for the matchmaking algorithm.
       We don't want to save any matches right now, that's why it was removed here.
     */

}
