package com.WebRise.usersubscriptions.mapper;

import com.WebRise.usersubscriptions.dto.UserDTO;
import com.WebRise.usersubscriptions.model.User;

/**
 * Mapper class for converting between {@link User} entities and {@link UserDTO} objects.
 * <p>
 * This class provides static methods to facilitate the transformation of user data
 * between the entity and DTO layers, ensuring proper data handling and encapsulation.
 * </p>
 */
public class UserMapper {

    /**
     * Converts a {@link User} entity to a {@link UserDTO}.
     *
     * @param user the User entity to convert.
     * @return the corresponding UserDTO.
     */
    public static UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    /**
     * Converts a {@link UserDTO} to a {@link User} entity.
     *
     * @param dto the UserDTO to convert.
     * @return the corresponding User entity.
     */
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}