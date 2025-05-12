package com.WebRise.usersubscriptions.service;

import com.WebRise.usersubscriptions.dto.UserDTO;

/**
 * Interface for managing user-related operations.
 * <p>
 * This interface defines the methods for creating, retrieving, updating, and deleting users.
 * </p>
 */
public interface IUserService {

    /**
     * Creates a new user.
     *
     * @param userDTO the user data to create.
     * @return the created UserDTO.
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user to retrieve.
     * @return the UserDTO corresponding to the user.
     */
    UserDTO getUserById(Long id);

    /**
     * Updates an existing user.
     *
     * @param id      the ID of the user to update.
     * @param userDTO the updated user data.
     * @return the updated UserDTO.
     */
    UserDTO updateUser(Long id, UserDTO userDTO);

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete.
     */
    void deleteUser(Long id);
}