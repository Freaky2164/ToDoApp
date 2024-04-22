package de.dhbw.ase.todoapp.domain.notification.reminder;


import java.util.List;

import de.dhbw.ase.todoapp.abstraction.event.ReachedDueDateEvent;
import de.dhbw.ase.todoapp.domain.notification.Notification;
import de.dhbw.ase.todoapp.domain.todo.Todo;


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
