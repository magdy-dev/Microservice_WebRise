package com.WebRise.usersubscriptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for creating a new user.
 * <p>
 * This class is used to encapsulate the necessary information for user creation,
 * allowing for safe data transfer between layers of the application.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    /**
     * The name of the user to be created.
     */
    private String name;

    /**
     * The email address of the user to be created.
     */
    private String email;
}