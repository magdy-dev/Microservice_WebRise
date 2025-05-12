package com.WebRise.usersubscriptions.rest.GlobalException;

import com.WebRise.usersubscriptions.exception.SubscriptionNotFoundException;
import com.WebRise.usersubscriptions.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for managing application-wide exceptions.
 * <p>
 * This class handles specific exceptions related to user and subscription not found cases,
 * as well as any unexpected errors, providing a consistent response structure.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles {@link UserNotFoundException} and returns a 404 response.
     *
     * @param ex the UserNotFoundException to handle.
     * @return a ResponseEntity with the error details.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
        logger.error("UserNotFoundException: {}", ex.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Handles {@link SubscriptionNotFoundException} and returns a 404 response.
     *
     * @param ex the SubscriptionNotFoundException to handle.
     * @return a ResponseEntity with the error details.
     */
    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<Object> handleSubscriptionNotFound(SubscriptionNotFoundException ex) {
        logger.error("SubscriptionNotFoundException: {}", ex.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Handles generic exceptions and returns a 500 response.
     *
     * @param ex the Exception to handle.
     * @return a ResponseEntity with the error details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        logger.error("Unexpected error: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    /**
     * Builds a standardized error response.
     *
     * @param status the HTTP status.
     * @param message the error message.
     * @return a ResponseEntity containing the error details.
     */
    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }
}