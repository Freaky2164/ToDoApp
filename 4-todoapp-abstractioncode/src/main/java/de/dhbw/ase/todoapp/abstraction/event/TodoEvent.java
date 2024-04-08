package de.dhbw.ase.todoapp.abstraction.event;


public abstract class TodoEvent
{
    private final String message;

    protected TodoEvent(String message)
    {
        this.message = message;
    }


    public String getMessage()
    {
        return message;
    }
}
