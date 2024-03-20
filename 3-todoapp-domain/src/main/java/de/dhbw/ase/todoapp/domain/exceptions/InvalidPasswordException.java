package de.dhbw.ase.todoapp.domain.exceptions;


public class InvalidPasswordException extends RuntimeException
{
    public InvalidPasswordException()
    {
        super("The password that was provided is invalid.");
    }
}
