package de.dhbw.ase.todoapp.plugins.persistence;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.vo.Name;


public class NotificationRepositoryBridgeTest
{

    private SpringDataNotificationRepository springDataNotificationRepository;
    private NotificationRepositoryBridge notificationRepositoryBridge;

    @Before
    public void setUp()
    {
        springDataNotificationRepository = mock(SpringDataNotificationRepository.class);
        notificationRepositoryBridge = new NotificationRepositoryBridge();
        notificationRepositoryBridge.springDataNotificationRepository = springDataNotificationRepository;
    }


    @Test
    public void testFindNotificationById()
    {
        Notification notification = new Notification(UUID.randomUUID(), new Name("Test Notification"), "http://example.com");
        Mockito.when(springDataNotificationRepository.findById(notification.getId())).thenReturn(Optional.of(notification));

        Optional<Notification> result = notificationRepositoryBridge.findNotificationById(notification.getId());

        assertTrue(result.isPresent());
        assertEquals(notification, result.get());
    }


    @Test
    public void testFindNotificationsByUserId()
    {
        UUID userId = UUID.randomUUID();
        Notification notification1 = new Notification(userId, new Name("Test Notification 1"), "http://example.com");
        Notification notification2 = new Notification(userId, new Name("Test Notification 2"), "http://example.com");
        List<Notification> notifications = new ArrayList<>();
        notifications.add(notification1);
        notifications.add(notification2);
        Mockito.when(springDataNotificationRepository.findAllByUserId(userId)).thenReturn(notifications);

        List<Notification> result = notificationRepositoryBridge.findNotificationsByUserId(userId);

        assertEquals(notifications.size(), result.size());
        assertTrue(result.contains(notification1));
        assertTrue(result.contains(notification2));
    }


    @Test
    public void testSave()
    {
        Notification notification = new Notification(UUID.randomUUID(), new Name("Test Notification"), "http://example.com");
        Mockito.when(springDataNotificationRepository.save(notification)).thenReturn(notification);

        Notification savedNotification = notificationRepositoryBridge.save(notification);

        assertEquals(notification, savedNotification);
    }


    @Test
    public void testDelete()
    {
        Notification notification = new Notification(UUID.randomUUID(), new Name("Test Notification"), "http://example.com");

        notificationRepositoryBridge.delete(notification);

        Mockito.verify(springDataNotificationRepository).delete(notification);
    }
}
