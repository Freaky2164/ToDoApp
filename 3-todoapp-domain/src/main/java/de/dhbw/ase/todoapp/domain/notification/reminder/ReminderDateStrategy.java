package de.dhbw.ase.todoapp.domain.notification.reminder;


import java.util.List;

import de.dhbw.ase.todoapp.abstraction.event.ReachedReminderDateEvent;
import de.dhbw.ase.todoapp.domain.notification.Notification;
import de.dhbw.ase.todoapp.domain.todo.Todo;


public class ReminderDateStrategy implements ReminderStrategy
{
    @Override
    public void checkDate(Todo todo, List<Notification> notifications)
    {
        if (todo.hasReachedReminderDate())
        {
            for (Notification notification : notifications)
            {
                notification.notify(new ReachedReminderDateEvent(todo.getName().getValue(), todo.getDueDate().formatDate()));
            }
        }
    }
}
