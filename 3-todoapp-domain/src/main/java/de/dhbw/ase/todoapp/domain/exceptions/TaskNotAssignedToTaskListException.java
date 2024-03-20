package de.dhbw.ase.todoapp.domain.exceptions;


public class TaskNotAssignedToTaskListException extends RuntimeException
{
    public TaskNotAssignedToTaskListException()
    {
        super("A task must be assigned to a task list and cannot be created without one.");
    }
}
