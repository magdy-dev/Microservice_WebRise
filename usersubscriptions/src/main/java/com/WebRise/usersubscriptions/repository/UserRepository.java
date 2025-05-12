package com.WebRise.usersubscriptions.repository;

import com.WebRise.usersubscriptions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link User} entities.
 * <p>
 * This interface extends JpaRepository, providing CRUD operations for working
 * with users in the database.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // No additional methods are defined; inherits all CRUD operations from JpaRepository.
}