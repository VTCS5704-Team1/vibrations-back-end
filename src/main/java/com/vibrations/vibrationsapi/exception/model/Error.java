package com.vibrations.vibrationsapi.exception.model;

import lombok.Data;

/**
 * Standard Error message model. Used for Error handling
 */
@Data
public class Error {
    private String message;
    private int status;
    private Long timestamp;
}
