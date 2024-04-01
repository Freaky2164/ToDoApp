package de.dhbw.ase.todoapp.domain.entities.notification;


import java.util.UUID;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.vo.Name;


public class NotificationTest
{
    @Test(expected = NullPointerException.class)
    public void testConstructor_NullUserId()
    {
        new Notification(null, new Name("Test Notification"), "http://example.com");
    }


    @Test(expected = NullPointerException.class)
    public void testConstructor_NullName()
    {
        new Notification(UUID.randomUUID(), null, "http://example.com");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_BlankWebHookUrl()
    {
        new Notification(UUID.randomUUID(), new Name("Test Notification"), "");
    }
}
