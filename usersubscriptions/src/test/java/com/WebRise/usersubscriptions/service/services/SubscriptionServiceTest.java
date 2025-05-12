package com.WebRise.usersubscriptions.service.services;

import com.WebRise.usersubscriptions.dto.SubscriptionDTO;
import com.WebRise.usersubscriptions.exception.SubscriptionNotFoundException;
import com.WebRise.usersubscriptions.exception.UserNotFoundException;
import com.WebRise.usersubscriptions.mapper.SubscriptionMapper;
import com.WebRise.usersubscriptions.model.Subscription;
import com.WebRise.usersubscriptions.model.User;
import com.WebRise.usersubscriptions.repository.SubscriptionRepository;
import com.WebRise.usersubscriptions.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SubscriptionService subscriptionService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepare dummy user
        user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");
    }

    @Test
    void testAddSubscription_Success() {
        // Arrange
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setServiceName("Netflix");
        subscriptionDTO.setStartDate(LocalDate.now());
        subscriptionDTO.setEndDate(LocalDate.now().plusMonths(1));
        subscriptionDTO.setActive(true);

        Subscription subscriptionEntity = SubscriptionMapper.toEntity(subscriptionDTO);
        subscriptionEntity.setId(1L);
        subscriptionEntity.setUser(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscriptionEntity);

        // Act
        SubscriptionDTO result = subscriptionService.addSubscription(1L, subscriptionDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Netflix", result.getServiceName());
        verify(userRepository, times(1)).findById(1L);
        verify(subscriptionRepository, times(1)).save(any(Subscription.class));
    }

    @Test
    void testAddSubscription_UserNotFound() {
        // Arrange
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            subscriptionService.addSubscription(2L, subscriptionDTO);
        });
        verify(userRepository, times(1)).findById(2L);
    }

    @Test
    void testGetUserSubscriptions_Success() {
        // Arrange
        Subscription subscription1 = new Subscription(1L, user, "Netflix", LocalDate.now(), LocalDate.now().plusMonths(1), true);
        Subscription subscription2 = new Subscription(2L, user, "Spotify", LocalDate.now(), LocalDate.now().plusMonths(1), true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(subscriptionRepository.findByUser(user)).thenReturn(Arrays.asList(subscription1, subscription2));

        // Act
        List<SubscriptionDTO> result = subscriptionService.getUserSubscriptions(1L);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Netflix", result.get(0).getServiceName());
        verify(userRepository, times(1)).findById(1L);
        verify(subscriptionRepository, times(1)).findByUser(user);
    }

    @Test
    void testDeleteSubscription_Success() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setId(1L);

        when(subscriptionRepository.findById(1L)).thenReturn(Optional.of(subscription));

        // Act
        subscriptionService.deleteSubscription(1L);

        // Assert
        verify(subscriptionRepository, times(1)).delete(subscription);
    }

    @Test
    void testDeleteSubscription_NotFound() {
        // Arrange
        when(subscriptionRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SubscriptionNotFoundException.class, () -> {
            subscriptionService.deleteSubscription(99L);
        });
        verify(subscriptionRepository, times(1)).findById(99L);
    }

    @Test
    void testGetTopSubscriptions() {
        // Arrange
        Subscription subscription1 = new Subscription();
        Subscription subscription2 = new Subscription();
        Subscription subscription3 = new Subscription();

        when(subscriptionRepository.findTopSubscriptions()).thenReturn(Arrays.asList(subscription1, subscription2, subscription3));

        // Act
        List<Subscription> result = subscriptionService.getTopSubscriptions();

        // Assert
        assertEquals(3, result.size());
        verify(subscriptionRepository, times(1)).findTopSubscriptions();
    }
}
