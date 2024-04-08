package de.dhbw.ase.todoapp.abstraction.event;


public class TodoDeletedEvent extends TodoEvent
{
    public TodoDeletedEvent(String todoName)
    {
        super("Das To-Do \\\"" + todoName + "\\\" wurde am gelöscht!");
    }
}
