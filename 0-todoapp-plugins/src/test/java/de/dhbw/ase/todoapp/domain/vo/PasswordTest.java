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
        assertNotNull(password.getPassword());
    }


    @Test(expected = InvalidPasswordException.class)
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
        Password password2 = new Password("ValidPassword123", password1.getSalt());
        Password password3 = new Password("DifferentPassword", password1.getSalt());

        assertEquals(password1, password2);
        assertNotEquals(password1, password3);
    }
}
