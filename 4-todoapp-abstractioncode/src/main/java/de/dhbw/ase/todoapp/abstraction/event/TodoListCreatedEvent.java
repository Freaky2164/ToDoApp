package de.dhbw.ase.todoapp.abstraction.event;

public class TodoListCreatedEvent extends TodoEvent
{
    public TodoListCreatedEvent(String todoListName)
    {
        super("Es wurde eine neue To-Do-Liste \\\"" + todoListName + "\\\" erstellt!");
    }
}
