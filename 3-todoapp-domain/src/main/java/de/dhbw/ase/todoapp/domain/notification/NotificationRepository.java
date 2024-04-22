package de.dhbw.ase.todoapp.domain.notification;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface NotificationRepository
{
    Optional<Notification> findById(UUID notificationId);


    List<Notification> findAllByUserId(UUID userId);


    Notification save(Notification notification);


    void delete(Notification notification);
}
