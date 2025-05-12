package com.WebRise.usersubscriptions.mapper;

import com.WebRise.usersubscriptions.dto.SubscriptionDTO;
import com.WebRise.usersubscriptions.model.Subscription;

/**
 * Mapper class for converting between {@link Subscription} entities and {@link SubscriptionDTO} objects.
 * <p>
 * This class provides static methods to facilitate the transformation of subscription data
 * between the entity and DTO layers, ensuring proper data handling and encapsulation.
 * </p>
 */
public class SubscriptionMapper {

    /**
     * Converts a {@link Subscription} entity to a {@link SubscriptionDTO}.
     *
     * @param subscription the Subscription entity to convert.
     * @return the corresponding SubscriptionDTO.
     */
    public static SubscriptionDTO toDto(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(subscription.getId());
        dto.setServiceName(subscription.getServiceName());
        dto.setStartDate(subscription.getStartDate());
        dto.setEndDate(subscription.getEndDate());
        dto.setActive(subscription.isActive());
        return dto;
    }

    /**
     * Converts a {@link SubscriptionDTO} to a {@link Subscription} entity.
     *
     * @param dto the SubscriptionDTO to convert.
     * @return the corresponding Subscription entity.
     */
    public static Subscription toEntity(SubscriptionDTO dto) {
        Subscription subscription = new Subscription();
        subscription.setId(dto.getId());
        subscription.setServiceName(dto.getServiceName());
        subscription.setStartDate(dto.getStartDate());
        subscription.setEndDate(dto.getEndDate());
        subscription.setActive(dto.isActive());
        return subscription;
    }
}