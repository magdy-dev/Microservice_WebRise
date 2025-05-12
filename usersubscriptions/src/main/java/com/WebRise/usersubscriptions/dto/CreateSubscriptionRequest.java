package com.WebRise.usersubscriptions.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for creating a new subscription.
 * <p>
 * This class is used to encapsulate the necessary information for subscription creation,
 * allowing for safe data transfer between layers of the application.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubscriptionRequest {

    /**
     * The name of the service for the subscription (e.g., YouTube Premium, Netflix).
     */
    private String serviceName;

    /**
     * The date when the subscription starts.
     */
    private LocalDate startDate;

    /**
     * The date when the subscription ends.
     */
    private LocalDate endDate;

    /**
     * Indicates whether the subscription is currently active.
     */
    private boolean active;
}