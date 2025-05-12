package com.WebRise.usersubscriptions.service.services;

import com.WebRise.usersubscriptions.dto.SubscriptionDTO;
import com.WebRise.usersubscriptions.model.Subscription;
import com.WebRise.usersubscriptions.model.User;
import com.WebRise.usersubscriptions.exception.SubscriptionNotFoundException;
import com.WebRise.usersubscriptions.exception.UserNotFoundException;
import com.WebRise.usersubscriptions.mapper.SubscriptionMapper;
import com.WebRise.usersubscriptions.repository.SubscriptionRepository;
import com.WebRise.usersubscriptions.repository.UserRepository;
import com.WebRise.usersubscriptions.service.ISubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing subscriptions.
 * <p>
 * This class contains methods for adding, retrieving, and deleting subscriptions
 * associated with users. It interacts with the repositories and handles any business
 * logic related to subscriptions.
 * </p>
 */
@Service
public class SubscriptionService implements ISubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a SubscriptionService with the specified repositories.
     *
     * @param subscriptionRepository the SubscriptionRepository to be used.
     * @param userRepository         the UserRepository to be used.
     */
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Adds a new subscription for a specified user.
     *
     * @param userId         the ID of the user to whom the subscription will be added.
     * @param subscriptionDTO the subscription data to add.
     * @return the created SubscriptionDTO.
     */
    public SubscriptionDTO addSubscription(Long userId, SubscriptionDTO subscriptionDTO) {
        logger.info("Adding subscription for user id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Subscription subscription = SubscriptionMapper.toEntity(subscriptionDTO);
        subscription.setUser(user);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return SubscriptionMapper.toDto(savedSubscription);
    }

    /**
     * Retrieves all subscriptions for a specified user.
     *
     * @param userId the ID of the user whose subscriptions are to be retrieved.
     * @return a list of SubscriptionDTOs.
     */
    public List<SubscriptionDTO> getUserSubscriptions(Long userId) {
        logger.info("Getting subscriptions for user id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return subscriptionRepository.findByUser(user).stream()
                .map(SubscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a specified subscription.
     *
     * @param subId the ID of the subscription to delete.
     */
    public void deleteSubscription(Long subId) {
        logger.info("Deleting subscription with id: {}", subId);
        Subscription subscription = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new SubscriptionNotFoundException(subId));
        subscriptionRepository.delete(subscription);
    }

    /**
     * Retrieves the top subscriptions.
     *
     * @return a list of the top Subscription entities.
     */
    public List<Subscription> getTopSubscriptions() {
        logger.info("Getting Top 3 popular subscriptions");
        return subscriptionRepository.findTopSubscriptions();
    }
}