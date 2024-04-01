package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.entities.notification.Notification;


@Repository
public interface SpringDataNotificationRepository extends JpaRepository<Notification, UUID>
{
    List<Notification> findAllByUserId(final UUID userId);
}
