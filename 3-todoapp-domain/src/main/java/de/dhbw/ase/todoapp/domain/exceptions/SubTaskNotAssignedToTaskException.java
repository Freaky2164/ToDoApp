package de.dhbw.ase.todoapp.domain.exceptions;


public class SubTaskNotAssignedToTaskException extends RuntimeException
{
    public SubTaskNotAssignedToTaskException()
    {
        super("A subtask must be assigned to a task and cannot be created without it.");
    }
}