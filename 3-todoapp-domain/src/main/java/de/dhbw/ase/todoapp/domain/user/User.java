package de.dhbw.ase.todoapp.domain.user;


import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_user")
public class User
{
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Embedded
    private Email mail;

    @Embedded
    private Password password;

    protected User()
    {
        // default constructor for JPA
    }


    public User(final Email mail, final Password password)
    {
        Objects.requireNonNull(mail);
        Objects.requireNonNull(password);

        this.id = UUID.randomUUID();
        this.mail = mail;
        this.password = password;
    }


    public UUID getId()
    {
        return this.id;
    }


    public Email getEmail()
    {
        return this.mail;
    }


    public Password getPassword()
    {
        return this.password;
    }
}
