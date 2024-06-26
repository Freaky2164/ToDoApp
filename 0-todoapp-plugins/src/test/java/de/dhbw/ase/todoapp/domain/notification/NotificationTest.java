package de.dhbw.ase.todoapp.domain.notification;


import java.util.UUID;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.notification.Notification;
import de.dhbw.ase.todoapp.domain.notification.WebHook;
import de.dhbw.ase.todoapp.domain.todo.Name;


public class NotificationTest
{
    @Test(expected = NullPointerException.class)
    public void testConstructor_NullUserId()
    {
        new Notification(null, new Name("Test Notification"), new WebHook("https://discord.com/api/webhooks/123"));
    }


    @Test(expected = NullPointerException.class)
    public void testConstructor_NullName()
    {
        new Notification(UUID.randomUUID(), null, new WebHook("https://discord.com/api/webhooks/123"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_BlankWebHookUrl()
    {
        new Notification(UUID.randomUUID(), new Name("Test Notification"), new WebHook(""));
    }
}
