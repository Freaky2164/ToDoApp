package de.dhbw.ase.todoapp.abstraction.event;


public class TodoCreatedEvent extends TodoEvent
{
    public TodoCreatedEvent(String todoName)
    {
        super("Es wurde ein neues To-Do \\\"" + todoName + "\\\" erstellt!");
    }
}
