package com.WebRise.usersubscriptions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents a subscription to a service for a user.
 * <p>
 * This class maps to the "subscriptions" table in the database and contains information
 * about the subscription including the service name, start and end dates, status,
 * and the associated user.
 * </p>
 */
@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    /**
     * The unique identifier for the subscription.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the service (e.g., YouTube Premium, Netflix).
     * This field cannot be null.
     */
    @Column(nullable = false)
    private String serviceName;  // e.g. YouTube Premium, Netflix

    /**
     * The date when the subscription starts.
     * This field cannot be null.
     */
    @Column(nullable = false)
    private LocalDate startDate;

    /**
     * The date when the subscription ends.
     * This field cannot be null.
     */
    @Column(nullable = false)
    private LocalDate endDate;

    /**
     * Indicates whether the subscription is currently active.
     * Defaults to true.
     */
    @Column(nullable = false)
    private boolean active = true;

    /**
     * The user associated with this subscription.
     * This field cannot be null.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}