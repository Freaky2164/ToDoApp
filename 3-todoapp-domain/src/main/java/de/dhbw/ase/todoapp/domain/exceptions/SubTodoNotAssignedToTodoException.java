package de.dhbw.ase.todoapp.domain.exceptions;


public class SubTodoNotAssignedToTodoException extends RuntimeException
{
    public SubTodoNotAssignedToTodoException()
    {
        super("A sub todo must be assigned to a todo and cannot be created without it.");
    }
}