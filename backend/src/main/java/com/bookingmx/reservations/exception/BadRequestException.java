package com.bookingmx.reservations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a client sends an invalid or malformed request.
 *
 * <p>This exception is typically used for:
 * <ul>
 *   <li>Invalid or incomplete reservation data</li>
 *   <li>Invalid date ranges (e.g., check-out before check-in)</li>
 *   <li>Attempting operations on a canceled reservation</li>
 * </ul>
 * </p>
 *
 * <p>The {@link ResponseStatus} annotation ensures that Spring automatically
 * returns an HTTP <strong>400 Bad Request</strong> response when this exception
 * is thrown, without requiring additional controller-level handling.</p>
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * Constructs a new BadRequestException with a descriptive message.
     *
     * @param message a human-readable explanation of the error
     */
    public BadRequestException(String message) {
        super(message);
    }
}
