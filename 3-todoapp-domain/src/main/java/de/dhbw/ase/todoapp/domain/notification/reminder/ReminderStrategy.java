package de.dhbw.ase.todoapp.domain.notification.reminder;


import java.util.List;

import de.dhbw.ase.todoapp.domain.notification.Notification;
import de.dhbw.ase.todoapp.domain.todo.Todo;


public interface ReminderStrategy
{
    void checkDate(Todo todo, List<Notification> notifications);
}
