package com.WebRise.usersubscriptions.rest.controller;

import com.WebRise.usersubscriptions.dto.SubscriptionDTO;
import com.WebRise.usersubscriptions.model.Subscription;
import com.WebRise.usersubscriptions.service.services.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing subscriptions related to users.
 * <p>
 * This class handles HTTP requests for adding, retrieving, and deleting subscriptions
 * associated with users. It interacts with the {@link SubscriptionService} to perform
 * the necessary business logic.
 * </p>
 */
@RestController
@RequestMapping("/users")
public class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);
    private final SubscriptionService subscriptionService;

    /**
     * Constructs a SubscriptionController with the specified SubscriptionService.
     *
     * @param subscriptionService the SubscriptionService to be used by this controller.
     */
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * Adds a new subscription for a specified user.
     *
     * @param id  the ID of the user to whom the subscription will be added.
     * @param dto the subscription data to add.
     * @return a ResponseEntity containing the added SubscriptionDTO.
     */
    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<SubscriptionDTO> addSubscription(@PathVariable Long id, @RequestBody SubscriptionDTO dto) {
        logger.info("POST /users/{}/subscriptions - Add Subscription: {}", id, dto.getServiceName());
        return ResponseEntity.ok(subscriptionService.addSubscription(id, dto));
    }

    /**
     * Retrieves all subscriptions for a specified user.
     *
     * @param id the ID of the user whose subscriptions are to be retrieved.
     * @return a ResponseEntity containing a list of SubscriptionDTOs.
     */
    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions(@PathVariable Long id) {
        logger.info("GET /users/{}/subscriptions - Get Subscriptions", id);
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(id));
    }

    /**
     * Deletes a specified subscription for a user.
     *
     * @param userId the ID of the user who owns the subscription.
     * @param subId  the ID of the subscription to delete.
     * @return a ResponseEntity with no content.
     */
    @DeleteMapping("/{userId}/subscriptions/{subId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long userId, @PathVariable Long subId) {
        logger.info("DELETE /users/{}/subscriptions/{} - Delete Subscription", userId, subId);
        subscriptionService.deleteSubscription(subId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the top subscriptions.
     *
     * @return a ResponseEntity containing a list of the top Subscription entities.
     */
    @GetMapping("/subscriptions/top")
    public ResponseEntity<List<Subscription>> getTopSubscriptions() {
        logger.info("GET /subscriptions/top - Top 3 Subscriptions");
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }
}