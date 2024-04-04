package de.dhbw.ase.todoapp.domain.exceptions;


public class DescriptionTooLongException extends RuntimeException
{
    public DescriptionTooLongException()
    {
        super("The description must not contain more than 250 characters.");
    }
}