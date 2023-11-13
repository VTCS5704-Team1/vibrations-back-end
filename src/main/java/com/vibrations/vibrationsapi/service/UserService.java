package com.vibrations.vibrationsapi.service;

import com.vibrations.vibrationsapi.dto.SignInRequestDto;
import com.vibrations.vibrationsapi.dto.SignInResponseDto;
import com.vibrations.vibrationsapi.dto.SignUpRequestDto;
import com.vibrations.vibrationsapi.dto.SignUpResponseDto;

public interface UserService {
    SignUpResponseDto signUp(SignUpRequestDto signUpRequest);

    SignInResponseDto signIn(SignInRequestDto signInRequest);
}
