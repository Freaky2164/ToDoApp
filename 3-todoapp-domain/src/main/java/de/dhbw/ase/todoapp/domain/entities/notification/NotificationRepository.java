package de.dhbw.ase.todoapp.domain.entities.notification;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface NotificationRepository
{

    Optional<Notification> findNotificationById(UUID notificationId);


    List<Notification> findNotificationsByUserId(UUID userId);


    Notification save(Notification notification);


    void delete(Notification notification);
}
