package de.dhbw.ase.todoapp.domain.vo;


import static org.junit.Assert.*;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.exceptions.InvalidWebHookUrlException;


public class WebHookTest
{

    @Test
    public void testConstructorAndGetUrl()
    {
        String url = "https://discord.com/api/webhooks/123456789";
        WebHook webHook = new WebHook(url);
        assertNotNull(webHook);
        assertEquals(url, webHook.getUrl());
    }


    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullUrl()
    {
        new WebHook(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyUrl()
    {
        new WebHook("");
    }


    @Test(expected = InvalidWebHookUrlException.class)
    public void testConstructorWithInvalidUrl()
    {
        String invalidUrl = "https://invalidurl.com";
        new WebHook(invalidUrl);
    }


    @Test
    public void testChangeUrl()
    {
        String initialUrl = "https://discord.com/api/webhooks/123456789";
        String newUrl = "https://discord.com/api/webhooks/987654321";
        WebHook webHook = new WebHook(initialUrl);
        WebHook newWebHook = webHook.changeUrl(newUrl);
        assertNotNull(newWebHook);
        assertEquals(newUrl, newWebHook.getUrl());
    }


    @Test
    public void testEquals()
    {
        String url1 = "https://discord.com/api/webhooks/123456789";
        String url2 = "https://discord.com/api/webhooks/987654321";
        WebHook webHook1 = new WebHook(url1);
        WebHook webHook2 = new WebHook(url1);
        WebHook webHook3 = new WebHook(url2);

        assertEquals(webHook1, webHook2);
        assertNotEquals(webHook1, webHook3);
    }
}
