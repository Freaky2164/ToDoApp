package de.dhbw.ase.todoapp.application;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.domain.notification.Notification;
import de.dhbw.ase.todoapp.domain.notification.NotificationRepository;
import de.dhbw.ase.todoapp.domain.user.User;


@Service
public class NotificationService
{
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(final NotificationRepository notificationRepository)
    {
        this.notificationRepository = notificationRepository;
    }


    public Optional<Notification> findById(UUID notificationId)
    {
        return notificationRepository.findById(notificationId);
    }


    public List<Notification> findAllForUser(User user)
    {
        return notificationRepository.findAllByUserId(user.getId());
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
