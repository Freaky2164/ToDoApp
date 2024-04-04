package de.dhbw.ase.todoapp.domain.exceptions;


public class UserAlreadyRegisteredException extends RuntimeException
{
    public UserAlreadyRegisteredException(String mailAdress)
    {
        super("There already exists a user with the provided mail adress: " + mailAdress);
    }
}
