package de.dhbw.ase.todoapp.domain.user;


import static org.junit.Assert.*;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.user.Password;
import de.dhbw.ase.todoapp.domain.user.User;
import de.dhbw.ase.todoapp.domain.user.UserFactory;


public class UserFactoryTest
{
    @Test
    public void testCreateUser()
    {
        String email = "test@example.com";
        String password = "Password123";

        User user = UserFactory.createUser(email, password);

        assertNotNull(user);
        assertEquals(email, user.getEmail().getMailAdress());
        assertEquals(new Password(password, user.getPassword().getSalt()), user.getPassword());
    }
}
