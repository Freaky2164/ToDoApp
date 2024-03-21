package de.dhbw.ase.todoapp.domain.vo;


import static org.junit.Assert.*;

import org.junit.Test;


public class DescriptionTest
{

    @Test
    public void testValidDescription()
    {
        String validDescription = "This is a valid description.";
        Description description = new Description(validDescription);
        assertEquals(validDescription, description.getDescription());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testBlankDescription()
    {
        String blankDescription = "";
        new Description(blankDescription);
    }


    @Test(expected = NullPointerException.class)
    public void testNullDescription()
    {
        String nullDescription = null;
        new Description(nullDescription);
    }


    @Test
    public void testChangeDescription()
    {
        String initialDescription = "Initial description.";
        Description description = new Description(initialDescription);

        String newDescription = "New description.";
        Description changedDescription = description.changeDescription(newDescription);

        assertEquals(newDescription, changedDescription.getDescription());
    }


    @Test
    public void testEquals()
    {
        Description description1 = new Description("Same description");
        Description description2 = new Description("Same description");
        Description description3 = new Description("Different description");

        assertTrue(description1.equals(description2));
        assertFalse(description1.equals(description3));
    }
}
