package de.dhbw.ase.todoapp.domain.vo;


import static org.junit.Assert.*;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.exceptions.InvalidEmailException;


public class EmailTest
{
    @Test
    public void testValidEmail()
    {
        String validEmail = "test@example.com";
        Email email = new Email(validEmail);
        assertEquals(validEmail, email.getMailAdress());
    }


    @Test(expected = InvalidEmailException.class)
    public void testBlankEmail()
    {
        String blankEmail = "";
        new Email(blankEmail);
    }


    @Test(expected = NullPointerException.class)
    public void testNullEmail()
    {
        String nullEmail = null;
        new Email(nullEmail);
    }


    @Test(expected = InvalidEmailException.class)
    public void testInvalidEmail()
    {
        String invalidEmail = "invalidemail@";
        new Email(invalidEmail);
    }


    @Test
    public void testEquals()
    {
        Email email1 = new Email("test@example.com");
        Email email2 = new Email("test@example.com");
        Email email3 = new Email("another@example.com");

        assertTrue(email1.equals(email2));
        assertFalse(email1.equals(email3));
    }
}
