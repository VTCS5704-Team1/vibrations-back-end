package com.vibrations.vibrationsapi.exception.model;

import lombok.Data;

@Data
public class Error {
    private String message;
    private int status;
    private Long timestamp;
}
