package de.dhbw.ase.todoapp.domain.user;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.user.Email;
import de.dhbw.ase.todoapp.domain.user.Password;
import de.dhbw.ase.todoapp.domain.user.User;


public class UserTest
{
    private Email email;
    private Password password;

    @Before
    public void setUp()
    {
        email = new Email("test@example.com");
        password = new Password("TestPassword123");
    }


    @Test
    public void testUserCreation()
    {
        User user = new User(email, password);
        assertNotNull(user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }


    @Test(expected = NullPointerException.class)
    public void testUserCreationWithNullEmail()
    {
        new User(null, password);
    }


    @Test(expected = NullPointerException.class)
    public void testUserCreationWithNullPassword()
    {
        new User(email, null);
    }
}
