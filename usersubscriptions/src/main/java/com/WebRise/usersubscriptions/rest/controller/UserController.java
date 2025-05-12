package com.WebRise.usersubscriptions.rest.controller;

import com.WebRise.usersubscriptions.dto.UserDTO;
import com.WebRise.usersubscriptions.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing user-related operations.
 * <p>
 * This class handles HTTP requests for creating, retrieving, updating, and deleting users.
 * It interacts with the {@link UserService} to perform the necessary business logic.
 * </p>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    /**
     * Constructs a UserController with the specified UserService.
     *
     * @param userService the UserService to be used by this controller.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles the creation of a new user.
     *
     * @param userDTO the user data to create.
     * @return a ResponseEntity containing the created UserDTO.
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        logger.info("POST /users - Create User: {}", userDTO.getName());
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user to retrieve.
     * @return a ResponseEntity containing the UserDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        logger.info("GET /users/{} - Get User", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update.
     * @param userDTO the updated user data.
     * @return a ResponseEntity containing the updated UserDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        logger.info("PUT /users/{} - Update User", id);
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete.
     * @return a ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("DELETE /users/{} - Delete User", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}