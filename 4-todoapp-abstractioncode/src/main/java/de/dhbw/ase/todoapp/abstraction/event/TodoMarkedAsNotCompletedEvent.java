package de.dhbw.ase.todoapp.abstraction.event;


public class TodoMarkedAsNotCompletedEvent extends TodoEvent
{
    public TodoMarkedAsNotCompletedEvent(String todoName)
    {
        super("Das To-Do \\\"" + todoName + "\\\" ist nicht länger abgeschlossen!");
    }
}
