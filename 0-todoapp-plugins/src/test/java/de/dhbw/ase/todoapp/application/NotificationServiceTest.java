package de.dhbw.ase.todoapp.application;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.notification.Notification;
import de.dhbw.ase.todoapp.domain.notification.NotificationRepository;
import de.dhbw.ase.todoapp.domain.user.User;


public class NotificationServiceTest
{
    private NotificationService notificationService;
    private NotificationRepository notificationRepository;
    private User user;
    private Notification notification;

    @Before
    public void setUp()
    {
        notificationRepository = mock(NotificationRepository.class);
        notificationService = new NotificationService(notificationRepository);

        user = mock(User.class);
        when(user.getId()).thenReturn(UUID.randomUUID());

        notification = mock(Notification.class);
    }


    @Test
    public void testFindById_NotificationFound()
    {
        UUID notificationId = UUID.randomUUID();
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));

        Optional<Notification> result = notificationService.findById(notificationId);

        assertTrue(result.isPresent());
        assertEquals(notification, result.get());
    }


    @Test
    public void testFindById_NotificationNotFound()
    {
        UUID notificationId = UUID.randomUUID();
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());

        Optional<Notification> result = notificationService.findById(notificationId);

        assertFalse(result.isPresent());
    }


    @Test
    public void testFindAllForUser()
    {
        List<Notification> expectedNotifications = new ArrayList<>();
        expectedNotifications.add(notification);
        when(notificationRepository.findAllByUserId(user.getId())).thenReturn(expectedNotifications);

        List<Notification> result = notificationService.findAllForUser(user);

        assertEquals(expectedNotifications, result);
    }


    @Test
    public void testCreateNotification()
    {
        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification result = notificationService.createNotification(notification);

        assertEquals(notification, result);
    }


    @Test
    public void testDeleteNotification()
    {
        notificationService.deleteNotification(notification);

        verify(notificationRepository).delete(notification);
    }
}
