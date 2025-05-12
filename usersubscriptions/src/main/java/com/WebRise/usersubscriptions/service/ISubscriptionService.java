package com.WebRise.usersubscriptions.service;

import com.WebRise.usersubscriptions.dto.SubscriptionDTO;
import com.WebRise.usersubscriptions.model.Subscription;

import java.util.List;

/**
 * Interface for managing subscription-related operations.
 * <p>
 * This interface defines the methods for adding, retrieving, and deleting subscriptions,
 * as well as fetching the top subscriptions.
 * </p>
 */
public interface ISubscriptionService {

    /**
     * Adds a new subscription for a specified user.
     *
     * @param userId         the ID of the user to whom the subscription will be added.
     * @param subscriptionDTO the subscription data to add.
     * @return the created SubscriptionDTO.
     */
    SubscriptionDTO addSubscription(Long userId, SubscriptionDTO subscriptionDTO);

    /**
     * Retrieves all subscriptions for a specified user.
     *
     * @param userId the ID of the user whose subscriptions are to be retrieved.
     * @return a list of SubscriptionDTOs.
     */
    List<SubscriptionDTO> getUserSubscriptions(Long userId);

    /**
     * Deletes a specified subscription.
     *
     * @param subId the ID of the subscription to delete.
     */
    void deleteSubscription(Long subId);

    /**
     * Retrieves the top subscriptions.
     *
     * @return a list of the top Subscription entities.
     */
    List<Subscription> getTopSubscriptions();
}