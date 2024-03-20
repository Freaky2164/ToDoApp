package de.dhbw.ase.todoapp.domain.entities.todo;


import java.time.LocalDate;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.vo.CalendarDate;
import de.dhbw.ase.todoapp.domain.vo.Description;
import de.dhbw.ase.todoapp.domain.vo.Name;


public class TaskFactory
{
    public static Task createTask(UUID taskListId, String name, String description, LocalDate dueDate, LocalDate reminderDate)
    {
        Name taskName = new Name(name);
        Description taskDescription = new Description(description);
        CalendarDate futureDueDate = new CalendarDate(dueDate);
        CalendarDate futureReminderDate = new CalendarDate(reminderDate);
        return new Task(taskListId, taskName, taskDescription, futureDueDate, futureReminderDate, false);
    }


    public static Task createSubTask(UUID taskListId, UUID taskId, String name, String description, LocalDate dueDate,
                                     LocalDate reminderDate)
    {
        Name taskName = new Name(name);
        Description taskDescription = new Description(description);
        CalendarDate futureDueDate = new CalendarDate(dueDate);
        CalendarDate futureReminderDate = new CalendarDate(reminderDate);
        return new Task(taskListId, taskId, taskName, taskDescription, futureDueDate, futureReminderDate, false);
    }
}
