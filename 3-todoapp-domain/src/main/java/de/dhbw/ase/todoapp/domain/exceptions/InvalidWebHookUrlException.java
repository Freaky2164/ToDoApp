package de.dhbw.ase.todoapp.domain.exceptions;


public class InvalidWebHookUrlException extends RuntimeException
{
    public InvalidWebHookUrlException()
    {
        super("The webhook url that was provided is invalid.");
    }
}
