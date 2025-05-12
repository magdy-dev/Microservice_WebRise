package com.WebRise.usersubscriptions.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for transferring subscription data.
 * <p>
 * This class is used to encapsulate subscription information, allowing for
 * safe data transfer between layers of the application.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {

    /**
     * The unique identifier for the subscription.
     */
    private Long id;

    /**
     * The unique identifier for the user associated with the subscription.
     */
    private Long userId;

    /**
     * The name of the service (e.g., YouTube Premium, Netflix).
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