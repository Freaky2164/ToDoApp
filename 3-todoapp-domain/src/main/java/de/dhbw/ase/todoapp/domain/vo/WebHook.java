package de.dhbw.ase.todoapp.domain.vo;


import java.util.Objects;

import org.apache.commons.lang3.Validate;

import de.dhbw.ase.todoapp.domain.exceptions.InvalidWebHookUrlException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class WebHook
{
    private static final String WEBHOOK_URL_PREFIX = "https://discord.com/api/webhooks/";

    @Column(name = "url", nullable = false)
    private final String url;

    protected WebHook()
    {
        // default constructor for JPA
        this.url = "";
    }


    public WebHook(final String url)
    {
        Validate.notBlank(url);
        if (!isValid(url))
        {
            throw new InvalidWebHookUrlException();
        }
        this.url = url;
    }


    private boolean isValid(final String url)
    {
        return url.startsWith(WEBHOOK_URL_PREFIX);
    }


    public String getUrl()
    {
        return url;
    }


    public WebHook changeUrl(final String url)
    {
        return new WebHook(url);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(url);
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        WebHook other = (WebHook)obj;
        return Objects.equals(url, other.url);
    }


    @Override
    public String toString()
    {
        return "WebHook [url=" + url + "]";
    }
}
