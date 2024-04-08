package de.dhbw.ase.todoapp.domain.entities.todo;


import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.exceptions.SubTodoNotAssignedToTodoException;
import de.dhbw.ase.todoapp.domain.exceptions.TodoNotAssignedToTodoListException;
import de.dhbw.ase.todoapp.domain.vo.CalendarDate;
import de.dhbw.ase.todoapp.domain.vo.Description;
import de.dhbw.ase.todoapp.domain.vo.Name;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_todo")
public class Todo
{
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "todoListId", nullable = false)
    private UUID todoListId;

    @Column(name = "todoId")
    private UUID todoId;

    @Embedded
    private Name name;

    @Embedded
    private Description description;

    @Embedded
    @AttributeOverride(name = "date", column = @Column(name = "due_date"))
    private CalendarDate dueDate;

    @Embedded
    @AttributeOverride(name = "date", column = @Column(name = "reminder_date"))
    private CalendarDate reminderDate;

    @Column(name = "done", nullable = false)
    private boolean done;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    protected Todo()
    {
        // default constructor for JPA
    }


    public Todo(final UUID todoListId,
                final Name name,
                final Description description,
                final CalendarDate dueDate,
                final CalendarDate reminder,
                final boolean done)
    {
        if (todoListId == null)
        {
            throw new TodoNotAssignedToTodoListException();
        }
        Objects.requireNonNull(name);
        Objects.requireNonNull(dueDate);
        Objects.requireNonNull(reminder);
        Objects.requireNonNull(done);

        this.id = UUID.randomUUID();
        this.todoListId = todoListId;
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
        this.reminderDate = reminder;
        this.done = done;
        this.creationDate = LocalDate.now();
    }


    public Todo(final UUID todoListId,
                final UUID taskId,
                final Name name,
                final Description description,
                final CalendarDate dueDate,
                final CalendarDate reminder,
                final boolean done)
    {
        if (todoListId == null)
        {
            throw new TodoNotAssignedToTodoListException();
        }
        if (taskId == null)
        {
            throw new SubTodoNotAssignedToTodoException();
        }
        Objects.requireNonNull(name);
        Objects.requireNonNull(dueDate);
        Objects.requireNonNull(reminder);
        Objects.requireNonNull(done);

        this.id = UUID.randomUUID();
        this.todoListId = todoListId;
        this.todoId = taskId;
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
        this.reminderDate = reminder;
        this.done = done;
        this.creationDate = LocalDate.now();
    }


    public UUID getId()
    {
        return id;
    }


    public UUID getTodoListId()
    {
        return todoListId;
    }


    public UUID getTodoId()
    {
        return todoId;
    }


    public Name getName()
    {
        return name;
    }


    public void setName(Name name)
    {
        this.name = name;
    }


    public Description getDescription()
    {
        return description;
    }


    public void setDescription(Description description)
    {
        this.description = description;
    }


    public CalendarDate getDueDate()
    {
        return dueDate;
    }


    public void setDueDate(CalendarDate dueDate)
    {
        this.dueDate = dueDate;
    }


    public CalendarDate getReminderDate()
    {
        return reminderDate;
    }


    public void setReminderDate(CalendarDate reminderDate)
    {
        this.reminderDate = reminderDate;
    }


    public boolean isDone()
    {
        return done;
    }


    public LocalDate getCreationDate()
    {
        return creationDate;
    }


    public LocalDate getCompletionDate()
    {
        return completionDate;
    }


    public boolean hasReachedReminderDate()
    {
        return reminderDate.isPastDate();
    }


    public boolean hasReachedDueDate()
    {
        return dueDate.isPastDate();
    }


    public void setAsFinished()
    {
        this.done = true;
        this.completionDate = LocalDate.now();
    }


    public void setAsNotFinished()
    {
        this.done = false;
        this.completionDate = null;
    }
}
