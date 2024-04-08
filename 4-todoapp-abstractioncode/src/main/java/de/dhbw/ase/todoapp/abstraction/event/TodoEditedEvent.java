package de.dhbw.ase.todoapp.abstraction.event;


public class TodoEditedEvent extends TodoEvent
{
    public TodoEditedEvent(String todoName)
    {
        super("Das To-Do \\\"" + todoName + "\\\" wurde bearbeitet!");
    }
}
