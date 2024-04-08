package de.dhbw.ase.todoapp.abstraction.event;


public class ReachedDueDateEvent extends TodoEvent
{
    public ReachedDueDateEvent(String todoName, String dueDate)
    {
        super("Das To-Do \\\"" + todoName + "\\\" wurde nicht zum eingetragenen Datum, dem " + dueDate + ", abgeschlossen!");
    }
}
