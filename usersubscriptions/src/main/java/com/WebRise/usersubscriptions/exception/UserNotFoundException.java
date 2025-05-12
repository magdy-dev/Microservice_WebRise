package com.WebRise.usersubscriptions.exception;

/**
 * Exception thrown when a user is not found in the system.
 * <p>
 * This exception extends {@link RuntimeException} and is used to indicate
 * that a user with a specified ID does not exist.
 * </p>
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with a detailed message.
     *
     * @param id the ID of the user that was not found.
     */
    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
}