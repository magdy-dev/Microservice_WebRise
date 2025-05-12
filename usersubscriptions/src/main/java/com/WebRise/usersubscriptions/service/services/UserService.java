package com.WebRise.usersubscriptions.service.services;

import com.WebRise.usersubscriptions.dto.UserDTO;
import com.WebRise.usersubscriptions.model.User;
import com.WebRise.usersubscriptions.exception.UserNotFoundException;
import com.WebRise.usersubscriptions.mapper.UserMapper;
import com.WebRise.usersubscriptions.repository.UserRepository;
import com.WebRise.usersubscriptions.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user operations.
 * <p>
 * This class contains methods for creating, retrieving, updating, and deleting users.
 * It interacts with the UserRepository and handles any business logic related to users.
 * </p>
 */
@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    /**
     * Constructs a UserService with the specified UserRepository.
     *
     * @param userRepository the UserRepository to be used by this service.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user.
     *
     * @param userDTO the user data to create.
     * @return the created UserDTO.
     */
    public UserDTO createUser(UserDTO userDTO) {
        logger.info("Creating new user: {}", userDTO.getName());
        User user = UserMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user to retrieve.
     * @return the UserDTO corresponding to the user.
     */
    public UserDTO getUserById(Long id) {
        logger.info("Getting user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toDto(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id      the ID of the user to update.
     * @param userDTO the updated user data.
     * @return the updated UserDTO.
     */
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        logger.info("Updating user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        User updatedUser = userRepository.save(user);
        return UserMapper.toDto(updatedUser);
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete.
     */
    public void deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}