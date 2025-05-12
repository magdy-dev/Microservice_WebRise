package com.WebRise.usersubscriptions.repository;

import com.WebRise.usersubscriptions.model.Subscription;
import com.WebRise.usersubscriptions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Subscription} entities.
 * <p>
 * This interface extends JpaRepository, providing CRUD operations and
 * custom query methods for working with subscriptions in the database.
 * </p>
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Finds all subscriptions associated with a given user ID.
     *
     * @param userId the ID of the user whose subscriptions are to be retrieved.
     * @return a list of subscriptions associated with the specified user ID.
     */
    List<Subscription> findByUserId(Long userId);

    /**
     * Retrieves a list of subscriptions ordered by their start date in descending order.
     *
     * @return a list of subscriptions sorted from the most recent to the oldest.
     */
    @Query("SELECT s FROM Subscription s ORDER BY s.startDate DESC")
    public List<Subscription> findTopSubscriptions();

    /**
     * Finds all subscriptions associated with a given user.
     *
     * @param user the user whose subscriptions are to be retrieved.
     * @return a list of subscriptions associated with the specified user.
     */
    List<Subscription> findByUser(User user);
}