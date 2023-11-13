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
public class SignInResponseDto {
    private String accessToken;
    private String refreshToken;
    private String idToken;
    private String tokenType;
    private String scope;
    private Integer expiresIn;
}
