package de.dhbw.ase.todoapp.domain.exceptions;


public class InvalidLoginException extends RuntimeException
{
    public InvalidLoginException(String message)
    {
        super(message);
    }
}
