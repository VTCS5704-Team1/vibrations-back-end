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
public class DownloadUserResponseDto {
    private int statusCode;
    private String statusMessage;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String gender;
    // private byte[] pfp;
    private String[] topArtists;
    private String[] topSongs;
}
