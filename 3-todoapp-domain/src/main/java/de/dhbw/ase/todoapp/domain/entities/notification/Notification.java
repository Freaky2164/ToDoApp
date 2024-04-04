package de.dhbw.ase.todoapp.domain.entities.notification;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.UUID;

import de.dhbw.ase.todoapp.abstraction.observer.TodoObserver;
import de.dhbw.ase.todoapp.domain.vo.Name;
import de.dhbw.ase.todoapp.domain.vo.WebHook;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_notification")
public class Notification implements TodoObserver
{
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "userId", nullable = false)
    private UUID userId;

    @Embedded
    private Name name;

    @Embedded
    private WebHook webHook;

    protected Notification()
    {
        // default constructor for JPA
    }


    public Notification(final UUID userId, final Name name, final WebHook webHook)
    {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(webHook);

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
        this.webHook = webHook;
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


    public void changeName(Name name)
    {
        this.name = name;
    }


    public WebHook getWebHookUrl()
    {
        return webHook;
    }


    public void setWebHook(WebHook webHook)
    {
        this.webHook = webHook;
    }


    @Override
    public void notify(String message)
    {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(webHook.getUrl()))
                                         .header("Content-Type", "application/json")
                                         .POST(HttpRequest.BodyPublishers.ofString("{ \"content\": \"" + message + "\" }"))
                                         .build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
