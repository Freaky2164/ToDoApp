package de.dhbw.ase.todoapp.domain.todo;


import java.time.LocalDate;
import java.util.UUID;


public class TodoFactory
{
    public static Todo createTodo(UUID todoListId, String name, String description, LocalDate dueDate, LocalDate reminderDate)
    {
        Name todoName = new Name(name);
        Description taskDescription = new Description(description);
        CalendarDate futureDueDate = new CalendarDate(dueDate);
        CalendarDate futureReminderDate = new CalendarDate(reminderDate);
        return new Todo(todoListId, todoName, taskDescription, futureDueDate, futureReminderDate, false);
    }


    public static Todo createSubTodo(UUID todoListId, UUID todoId, String name, String description, LocalDate dueDate,
                                     LocalDate reminderDate)
    {
        Name todoName = new Name(name);
        Description taskDescription = new Description(description);
        CalendarDate futureDueDate = new CalendarDate(dueDate);
        CalendarDate futureReminderDate = new CalendarDate(reminderDate);
        return new Todo(todoListId, todoId, todoName, taskDescription, futureDueDate, futureReminderDate, false);
    }
}
