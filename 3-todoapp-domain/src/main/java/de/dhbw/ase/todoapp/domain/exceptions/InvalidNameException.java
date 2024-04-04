package de.dhbw.ase.todoapp.domain.exceptions;


public class InvalidNameException extends RuntimeException
{
    public InvalidNameException()
    {
        super("The name must not be null or empty.");
    }
}
