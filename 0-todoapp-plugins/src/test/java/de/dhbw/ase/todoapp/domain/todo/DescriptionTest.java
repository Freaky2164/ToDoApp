package de.dhbw.ase.todoapp.domain.todo;


import static org.junit.Assert.*;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.exceptions.DescriptionTooLongException;
import de.dhbw.ase.todoapp.domain.todo.Description;


public class DescriptionTest
{
    @Test
    public void testValidDescription()
    {
        String validDescription = "This is a valid description.";
        Description description = new Description(validDescription);
        assertEquals(validDescription, description.getDescription());
    }


    @Test(expected = DescriptionTooLongException.class)
    public void testNullDescription()
    {
        String tooLongDescription = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
                                 + "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, "
                                 + "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, "
                                 + "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum";
        new Description(tooLongDescription);
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
