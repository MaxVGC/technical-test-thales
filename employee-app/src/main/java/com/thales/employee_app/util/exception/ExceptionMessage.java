package com.thales.employee_app.util.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.SuperBuilder;


/**
 * Represents an exception message with status, code, and message.
 */
@Data
@SuperBuilder
public class ExceptionMessage {
    private HttpStatus status;
    private int code;
    private String message;
}
