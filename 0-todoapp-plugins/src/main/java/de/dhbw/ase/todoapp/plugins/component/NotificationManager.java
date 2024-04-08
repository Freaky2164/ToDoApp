package de.dhbw.ase.todoapp.plugins.component;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.dhbw.ase.todoapp.domain.entities.notification.Notification;


@Component
public class NotificationManager
{
    private List<Notification> notifications = new ArrayList<>();

    public void setNotifications(List<Notification> notifications)
    {
        this.notifications = notifications;
    }


    public List<Notification> getNotifications()
    {
        return notifications;
    }


    public void registerNotification(Notification notification)
    {
        this.notifications.add(notification);
    }


    public void unregisterNotification(Notification notification)
    {
        List<Notification> notificationsToRemove = new ArrayList<>();
        for (Notification registeredNotifications : notifications)
        {
            if (registeredNotifications.getId().equals(notification.getId()))
            {
                notificationsToRemove.add(notification);
            }
        }
        notifications.removeAll(notificationsToRemove);
    }
}
