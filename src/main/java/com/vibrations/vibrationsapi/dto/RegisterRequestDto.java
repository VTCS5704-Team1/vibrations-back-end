package com.vibrations.vibrationsapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String gender;
    private String[] topArtists;
    private String[] topSongs;
    //new
    private Long phoneNumber;
    private double maxDistance;
    private double latitude;
    private double longitude;
}
