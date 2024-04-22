package de.dhbw.ase.todoapp.domain.todo;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.exceptions.SubTodoNotAssignedToTodoException;
import de.dhbw.ase.todoapp.domain.exceptions.TodoNotAssignedToTodoListException;
import de.dhbw.ase.todoapp.domain.todo.CalendarDate;
import de.dhbw.ase.todoapp.domain.todo.Description;
import de.dhbw.ase.todoapp.domain.todo.Name;
import de.dhbw.ase.todoapp.domain.todo.Todo;


public class TodoTest
{
    private UUID todoListId;
    private Name name;
    private Description description;
    private CalendarDate dueDate;
    private CalendarDate reminderDate;
    private boolean done;

    @Before
    public void setUp()
    {
        todoListId = UUID.randomUUID();
        name = new Name("Todo Name");
        description = new Description("Todo Description");
        dueDate = new CalendarDate(LocalDate.now().plusDays(1));
        reminderDate = new CalendarDate(LocalDate.now());
        done = false;
    }


    @Test(expected = TodoNotAssignedToTodoListException.class)
    public void testTodoCreationWithoutTodoListId()
    {
        new Todo(null, name, description, dueDate, reminderDate, done);
    }


    @Test(expected = SubTodoNotAssignedToTodoException.class)
    public void testSubTodoCreationWithoutTodoId()
    {
        UUID todoId = null;
        new Todo(todoListId, todoId, name, description, dueDate, reminderDate, done);
    }


    @Test
    public void testTodoCreation()
    {
        Todo todo = new Todo(todoListId, name, description, dueDate, reminderDate, done);
        assertNotNull(todo.getId());
        assertEquals(todoListId, todo.getTodoListId());
        assertEquals(name, todo.getName());
        assertEquals(description, todo.getDescription());
        assertEquals(dueDate, todo.getDueDate());
        assertEquals(reminderDate, todo.getReminderDate());
        assertEquals(done, todo.isDone());
        assertNotNull(todo.getCreationDate());
        assertEquals(null, todo.getCompletionDate());
    }


    @Test
    public void testSetTodoAsFinished()
    {
        Todo Todo = new Todo(todoListId, name, description, dueDate, reminderDate, done);
        Todo.setAsFinished();
        assertTrue(Todo.isDone());
        assertNotNull(Todo.getCompletionDate());
    }


    @Test
    public void testSetTodoAsNotFinished()
    {
        Todo todo = new Todo(todoListId, name, description, dueDate, reminderDate, done);
        todo.setAsFinished();
        todo.setAsNotFinished();
        assertFalse(todo.isDone());
        assertEquals(null, todo.getCompletionDate());
    }
}
