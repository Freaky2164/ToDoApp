package de.dhbw.ase.todoapp.domain.vo;


import static org.junit.Assert.*;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.exceptions.InvalidPasswordException;


public class PasswordTest
{

    @Test
    public void testValidPassword()
    {
        String validPassword = "ValidPassword123";
        Password password = new Password(validPassword);
        assertEquals(validPassword, password.getPassword());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testBlankPassword()
    {
        String blankPassword = "";
        new Password(blankPassword);
    }


    @Test(expected = NullPointerException.class)
    public void testNullPassword()
    {
        String nullPassword = null;
        new Password(nullPassword);
    }


    @Test(expected = InvalidPasswordException.class)
    public void testShortPassword()
    {
        String shortPassword = "ShortPW";
        new Password(shortPassword);
    }


    @Test(expected = InvalidPasswordException.class)
    public void testInvalidCharactersPassword()
    {
        String shortPassword = "Password1!";
        new Password(shortPassword);
    }


    @Test
    public void testEquals()
    {
        Password password1 = new Password("ValidPassword123");
        Password password2 = new Password("ValidPassword123");
        Password password3 = new Password("DifferentPassword");

        assertTrue(password1.equals(password2));
        assertFalse(password1.equals(password3));
    }
}
