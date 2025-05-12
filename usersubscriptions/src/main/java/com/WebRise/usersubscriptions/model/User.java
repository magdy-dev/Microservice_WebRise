package com.WebRise.usersubscriptions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a user in the subscription system.
 * <p>
 * This class maps to the "users" table in the database and contains information
 * about the user, including their name, email, and associated subscriptions.
 * </p>
 */
@Entity
@Table(name = "users")  // Avoid reserved keyword conflict
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The list of subscriptions associated with this user.
     * The relationship is managed by the 'user' field in the Subscription class.
     * This field is lazily fetched and supports cascading operations.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Subscription> subscriptions;

}