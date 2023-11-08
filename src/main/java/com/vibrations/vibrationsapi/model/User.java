package com.vibrations.vibrationsapi.model;



import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

//@Data
@Entity
@Data
@Table(name = "users", schema = "vibrations_backend")
public class User {

    //@ManyToMany
    //private List<User> matches;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "profile_picture_link")
    private String profilePictureLink;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private Long phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "fav_song")
    private List<String> favSong;
    @Column(name = "fav_artist")
    private List<String> favArtist;
    @Column(name = "fav_genre")
    private List<String> favGenre;
    @Column(name = "score")
    private double score;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;



//    public List<User> getMatches() {
//        return matches;
//    }
//
//    public void setMatches(List<User> matches) {
//        this.matches = matches;
//    }



    // Constructors are generated by Lombok
}
