package com.vibrations.vibrationsapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequestDto {
    private String name;
    private String email;
    private String bio;
    private String gender;
    private String pfp; // ???
    private String[] topArtists; // Should only be 5
    private String[] topSongs; // Should only be 5
//    private double latitude;
//    private double longitude;
}
