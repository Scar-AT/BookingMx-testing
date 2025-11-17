package com.bookingmx.reservations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource cannot be found.
 *
 * <p>This exception is commonly used when attempting to access or modify a
 * reservation that does not exist in the system. By annotating the class with
 * {@link ResponseStatus}, Spring automatically maps it to an HTTP
 * <strong>404 Not Found</strong> response.</p>
 *
 * <p>No additional controller error handling is required, making this exception
 * ideal for simple REST-style validation flows.</p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with a descriptive error message.
     *
     * @param message a human-readable explanation of what resource was not found
     */
    public NotFoundException(String message) {
        super(message);
    }
}
