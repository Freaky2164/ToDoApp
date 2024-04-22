package de.dhbw.ase.todoapp.plugins.controller;


import org.springframework.beans.factory.annotation.Autowired;

import de.dhbw.ase.todoapp.abstraction.event.TodoEvent;
import de.dhbw.ase.todoapp.abstraction.observer.Observable;
import de.dhbw.ase.todoapp.abstraction.observer.TodoObserver;
import de.dhbw.ase.todoapp.application.NotificationService;
import de.dhbw.ase.todoapp.application.TodoService;
import de.dhbw.ase.todoapp.application.UserService;
import de.dhbw.ase.todoapp.domain.notification.Notification;
import de.dhbw.ase.todoapp.plugins.component.NotificationManager;


public abstract class BaseController extends Observable
{
    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationManager notificationManager;

    @Override
    public void registerObserver(TodoObserver observer)
    {
        if (observer instanceof Notification notification)
        {
            notificationManager.registerNotification(notification);
        }
    }


    @Override
    public void unregisterObserver(TodoObserver observer)
    {
        if (observer instanceof Notification notification)
        {
            notificationManager.unregisterNotification(notification);
        }
    }


    @Override
    public void notifyObservers(TodoEvent event)
    {
        for (Notification notification : notificationManager.getNotifications())
        {
            notification.notify(event);
        }
    }
}
