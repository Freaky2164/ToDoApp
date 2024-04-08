package de.dhbw.ase.todoapp.abstraction.event;


public class TodoMarkedAsCompletedEvent extends TodoEvent
{
    public TodoMarkedAsCompletedEvent(String todoName, String completionDate)
    {
        super("Das To-Do \\\"" + todoName + "\\\" wurde am " + completionDate + " abgeschlossen!");
    }
}
