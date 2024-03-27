package de.dhbw.ase.todoapp.domain.entities.todo;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.vo.Name;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_todo_list")
public class TodoList
{
    @Id
    @Column
    private UUID id;

    @Column
    private UUID userId;

    @Embedded
    private Name name;

    @OneToMany(mappedBy = "todoListId")
    private List<Todo> todos;

    protected TodoList()
    {
        // default constructor for JPA
    }


    public TodoList(final UUID userId, final Name name)
    {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(name);

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
        this.todos = new ArrayList<>();
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


    public List<Todo> getTodos()
    {
        return todos;
    }


    public void addTodo(Todo todo)
    {
        Objects.requireNonNull(todo);
        this.todos.add(todo);
    }


    public void removeTodo(Todo todo)
    {
        this.todos.remove(todo);
    }
}
