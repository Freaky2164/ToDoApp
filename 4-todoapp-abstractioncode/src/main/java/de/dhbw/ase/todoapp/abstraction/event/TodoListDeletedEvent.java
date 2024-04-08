package de.dhbw.ase.todoapp.abstraction.event;


public class TodoListDeletedEvent extends TodoEvent
{
    public TodoListDeletedEvent(String todoListName)
    {
        super("Die To-Do-Liste \\\"" + todoListName + "\\\" wurde gelöscht!");
    }
}
