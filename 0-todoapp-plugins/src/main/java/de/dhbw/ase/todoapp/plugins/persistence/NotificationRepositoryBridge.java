package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.notification.Notification;
import de.dhbw.ase.todoapp.domain.notification.NotificationRepository;


@Repository
public class NotificationRepositoryBridge implements NotificationRepository
{
    @Autowired
    SpringDataNotificationRepository springDataNotificationRepository;

    @Override
    public Optional<Notification> findById(UUID notificationId)
    {
        return springDataNotificationRepository.findById(notificationId);
    }


    @Override
    public List<Notification> findAllByUserId(UUID userId)
    {
        return springDataNotificationRepository.findAllByUserId(userId);
    }


    @Override
    public Notification save(Notification todoNotification)
    {
        return springDataNotificationRepository.save(todoNotification);
    }


    @Override
    public void delete(Notification todoNotification)
    {
        springDataNotificationRepository.delete(todoNotification);
    }
}
