package de.dhbw.ase.todoapp.domain.exceptions;


public class TodoNotAssignedToTodoListException extends RuntimeException
{
    public TodoNotAssignedToTodoListException()
    {
        super("A todo must be assigned to a todo list and cannot be created without one.");
    }
}
