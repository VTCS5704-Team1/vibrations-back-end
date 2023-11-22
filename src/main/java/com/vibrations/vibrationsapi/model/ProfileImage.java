package com.vibrations.vibrationsapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile_images")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;
}
