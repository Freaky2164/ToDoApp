package de.dhbw.ase.todoapp.application;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.entities.notification.NotificationRepository;


@Service
public class NotificationService
{
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(final NotificationRepository notificationRepository)
    {
        this.notificationRepository = notificationRepository;
    }


    public Optional<Notification> findNotificationById(UUID notificationId)
    {
        return notificationRepository.findNotificationById(notificationId);
    }


    public List<Notification> findNotificationByUserId(UUID userId)
    {
        return notificationRepository.findNotificationsByUserId(userId);
    }


    public Notification createNotification(Notification notification)
    {
        return notificationRepository.save(notification);
    }


    public void deleteNotification(Notification notification)
    {
        notificationRepository.delete(notification);
    }
}
