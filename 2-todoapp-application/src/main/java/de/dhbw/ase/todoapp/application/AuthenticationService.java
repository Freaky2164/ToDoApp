package de.dhbw.ase.todoapp.application;


import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.domain.vo.Password;


@Service
public class AuthenticationService
{
    public boolean authenticate(final Password storedPassword, final Password enteredPassword)
    {
        return storedPassword.equals(enteredPassword);
    }
}
