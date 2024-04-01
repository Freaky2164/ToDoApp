package de.dhbw.ase.todoapp.domain.entities.notification;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.Validate;

import de.dhbw.ase.todoapp.domain.vo.Name;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_notification")
public class Notification implements Observer
{
    @Id
    @Column
    private UUID id;

    @Column
    private UUID userId;

    @Embedded
    private Name name;

    @Column
    private String webHookUrl;

    protected Notification()
    {
        // default constructor for JPA
    }


    public Notification(final UUID userId, final Name name, final String webHookUrl)
    {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(name);
        Validate.notBlank(webHookUrl);

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
        this.webHookUrl = webHookUrl;
    }


    public UUID getId()
    {
        return id;
    }


    public UUID getUserId()
    {
        return userId;
    }


    public Name getName()
    {
        return name;
    }


    public void setName(Name name)
    {
        this.name = name;
    }


    public String getWebHookUrl()
    {
        return webHookUrl;
    }


    public void setWebHookUrl(String webHookUrl)
    {
        this.webHookUrl = webHookUrl;
    }


    @Override
    public void notify(String message)
    {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(webHookUrl))
                                         .header("Content-Type", "application/json")
                                         .POST(HttpRequest.BodyPublishers.ofString("{ \"content\": \"" + message + "\" }"))
                                         .build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
