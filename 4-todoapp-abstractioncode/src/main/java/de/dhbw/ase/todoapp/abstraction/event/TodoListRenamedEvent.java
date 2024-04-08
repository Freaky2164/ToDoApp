package de.dhbw.ase.todoapp.abstraction.event;

public class TodoListRenamedEvent extends TodoEvent
{
    public TodoListRenamedEvent(String oldName, String newName)
    {
        super("Die To-Do-Liste \\\"" + oldName + "\\\" wurde in \\\"" + newName + "\\\" umbenannt!");
    }
}
