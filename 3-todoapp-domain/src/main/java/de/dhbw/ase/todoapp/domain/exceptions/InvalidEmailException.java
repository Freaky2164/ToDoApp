package de.dhbw.ase.todoapp.domain.exceptions;


public class InvalidEmailException extends RuntimeException
{
    public InvalidEmailException()
    {
        super("The password that was provided is invalid.");
    }
}
