package de.dhbw.ase.todoapp.domain.entities.notification.reminder;


import java.util.List;

import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.entities.todo.Todo;


public class DueDateStrategy implements ReminderStrategy
{
    @Override
    public void checkDate(Todo todo, List<Notification> notifications)
    {
        if (todo.hasReachedCompletionDate())
        {
            for (Notification notification : notifications)
            {
                notification.notify("Das To-Do \\\"" + todo.getName() + "\\\" wurde nicht zum eingetragenen Datum, dem "
                                    + todo.getDueDate().formatDate() + ", abgeschlossen!");
            }
        }
    }
}
