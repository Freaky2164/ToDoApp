package de.dhbw.ase.todoapp.abstraction.event;


public class SubTodoCreatedEvent extends TodoEvent
{
    public SubTodoCreatedEvent(String subTodoName)
    {
        super("Es wurde ein neues Sub-To-Do \\\"" + subTodoName + "\\\" erstellt!");
    }
}
