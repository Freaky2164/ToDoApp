package de.dhbw.ase.todoapp.domain.entities.todo;


import java.util.Objects;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.vo.Name;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_task_list")
public class TaskList
{
    @Id
    @Column
    private UUID id;

    @Column
    private UUID userId;

    @Embedded
    private Name name;

    protected TaskList()
    {
        // default constructor for JPA
    }


    public TaskList(final UUID userId, final Name name)
    {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(name);

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
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
        Objects.requireNonNull(name);
        this.name = name;
    }
}
