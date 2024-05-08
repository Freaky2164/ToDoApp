package de.dhbw.ase.todoapp.abstraction.event;


public class ReachedReminderDateEvent extends TodoEvent
{
    public ReachedReminderDateEvent(String todoName, String reminderDate)
    {
        super("Erinnerung: Das To-Do \\\"" + todoName + "\\\" muss bis zum " + reminderDate + " abgeschlossen werden!");
    }
}
