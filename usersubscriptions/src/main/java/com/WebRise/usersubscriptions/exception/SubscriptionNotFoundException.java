package com.WebRise.usersubscriptions.exception;

/**
 * Exception thrown when a subscription is not found in the system.
 * <p>
 * This exception extends {@link RuntimeException} and is used to indicate
 * that a subscription with a specified ID does not exist.
 * </p>
 */
public class SubscriptionNotFoundException extends RuntimeException {

    /**
     * Constructs a new SubscriptionNotFoundException with a detailed message.
     *
     * @param subscriptionId the ID of the subscription that was not found.
     */
    public SubscriptionNotFoundException(Long subscriptionId) {
        super("Subscription with ID " + subscriptionId + " not found.");
    }
}