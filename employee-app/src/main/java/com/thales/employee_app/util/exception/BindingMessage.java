package com.thales.employee_app.util.exception;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Represents a binding message exception that contains a map of errors.
 * This class extends the {@link ExceptionMessage} class.
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class BindingMessage extends ExceptionMessage {
    private HashMap<String, String> errors;
}
