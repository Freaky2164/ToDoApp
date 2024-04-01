package de.dhbw.ase.todoapp.domain.entities.user;


import static org.junit.Assert.*;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.vo.Password;


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
