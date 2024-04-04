package de.dhbw.ase.todoapp.domain.vo;


import static org.junit.Assert.*;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.exceptions.InvalidNameException;


public class NameTest
{
    @Test
    public void testValidName()
    {
        String validName = "John Doe";
        Name name = new Name(validName);
        assertEquals(validName, name.getValue());
    }


    @Test(expected = InvalidNameException.class)
    public void testBlankName()
    {
        String blankName = "";
        new Name(blankName);
    }


    @Test(expected = InvalidNameException.class)
    public void testNullName()
    {
        String nullName = null;
        new Name(nullName);
    }


    @Test
    public void testChangeName()
    {
        String initialName = "Initial Name";
        Name name = new Name(initialName);

        String newName = "New Name";
        Name changedName = name.changeName(newName);

        assertEquals(newName, changedName.getValue());
    }


    @Test
    public void testEquals()
    {
        Name name1 = new Name("John Doe");
        Name name2 = new Name("John Doe");
        Name name3 = new Name("Jane Smith");

        assertTrue(name1.equals(name2));
        assertFalse(name1.equals(name3));
    }
}
