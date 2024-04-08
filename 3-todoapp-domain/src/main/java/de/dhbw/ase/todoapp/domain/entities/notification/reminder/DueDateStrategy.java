package de.dhbw.ase.todoapp.domain.entities.notification.reminder;


import java.util.List;

import de.dhbw.ase.todoapp.abstraction.event.ReachedDueDateEvent;
import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.entities.todo.Todo;


public class DueDateStrategy implements ReminderStrategy
{
    @Override
    public void checkDate(Todo todo, List<Notification> notifications)
    {
        if (todo.hasReachedDueDate())
        {
            for (Notification notification : notifications)
            {
                notification.notify(new ReachedDueDateEvent(todo.getName().getValue(), todo.getDueDate().formatDate()));
            }
        }
    }
}
