package de.dhbw.ase.todoapp.domain.entities.todo;


import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.exceptions.SubTaskNotAssignedToTaskException;
import de.dhbw.ase.todoapp.domain.exceptions.TaskNotAssignedToTaskListException;
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
@Table(name = "t_task")
public class Task
{
    @Id
    @Column
    private UUID id;

    @Column
    private UUID taskListId;

    @Column
    private UUID taskId;

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

    @Column(name = "done")
    private boolean done;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    protected Task()
    {
        // default constructor for JPA
    }


    public Task(final UUID taskListId,
                final Name name,
                final Description description,
                final CalendarDate dueDate,
                final CalendarDate reminder,
                final boolean done)
    {
        if (taskListId == null)
        {
            throw new TaskNotAssignedToTaskListException();
        }
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        Objects.requireNonNull(dueDate);
        Objects.requireNonNull(reminder);
        Objects.requireNonNull(done);

        this.id = UUID.randomUUID();
        this.taskListId = taskListId;
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
        this.reminderDate = reminder;
        this.done = done;
        this.creationDate = LocalDate.now();
    }


    public Task(final UUID taskListId,
                final UUID taskId,
                final Name name,
                final Description description,
                final CalendarDate dueDate,
                final CalendarDate reminder,
                final boolean done)
    {
        if (taskListId == null)
        {
            throw new TaskNotAssignedToTaskListException();
        }
        if (taskId == null)
        {
            throw new SubTaskNotAssignedToTaskException();
        }
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        Objects.requireNonNull(dueDate);
        Objects.requireNonNull(reminder);
        Objects.requireNonNull(done);

        this.id = UUID.randomUUID();
        this.taskListId = taskListId;
        this.taskId = taskId;
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


    public UUID getTaskListId()
    {
        return taskListId;
    }


    public UUID getTaskId()
    {
        return taskId;
    }


    public Name getName()
    {
        return name;
    }


    public Description getDescription()
    {
        return description;
    }


    public CalendarDate getDueDate()
    {
        return dueDate;
    }


    public CalendarDate getReminderDate()
    {
        return reminderDate;
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


    public void setTaskAsFinished()
    {
        this.done = true;
        this.completionDate = LocalDate.now();
    }


    public void setTaskAsNotFinished()
    {
        this.done = false;
        this.completionDate = null;
    }
}
