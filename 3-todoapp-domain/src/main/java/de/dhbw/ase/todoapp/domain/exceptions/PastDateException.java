package de.dhbw.ase.todoapp.domain.exceptions;


public class PastDateException extends RuntimeException
{
    public PastDateException()
    {
        super("The date cannot be in the past.");
    }
}
