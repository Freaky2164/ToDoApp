package de.dhbw.ase.todoapp.domain.entities.notification.reminder;


import java.util.List;

import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.entities.todo.Todo;


public interface ReminderStrategy
{
    void checkDate(Todo todo, List<Notification> notifications);
}
