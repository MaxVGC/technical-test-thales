package com.thales.employee_app.util.exception;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Global exception handler for RESTful APIs.
 * This class provides methods to handle different types of exceptions and return appropriate responses.
 */
@RestControllerAdvice
public class RestExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionMessage> handleRuntimeException(Exception ex, Locale locale) {
        System.out.println("Exception: " + ex.getMessage());
        return ResponseEntity.badRequest().body(
                ExceptionMessage.builder()
                        .code(400)
                        .status(HttpStatus.BAD_REQUEST)
                        .message(messageSource.getMessage(ex.getMessage(), null, locale))
                        .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<BindingMessage> handleHandlerMethodValidation(HandlerMethodValidationException ex,
            Locale locale) {
        HashMap<String, String> errorMap = new HashMap<>();
        ex.getAllErrors()
                .forEach(error -> errorMap.put(((DefaultMessageSourceResolvable) error.getArguments()[0]).getCodes()[1],
                        error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(
                BindingMessage.builder()
                        .code(400)
                        .status(HttpStatus.BAD_REQUEST)
                        .message(messageSource.getMessage("message.api.error.badrequest", null, locale))
                        .errors(errorMap)
                        .build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleException(Exception ex, Locale locale) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ExceptionMessage.builder()
                        .code(500)
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(messageSource.getMessage("message.internalServerError", null, locale))
                        .build());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Void> handleResourceNotFound(NoResourceFoundException ex, Locale locale) {
        return ResponseEntity.notFound().build();
    }
}
