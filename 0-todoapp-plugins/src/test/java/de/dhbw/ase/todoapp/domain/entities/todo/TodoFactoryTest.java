package de.dhbw.ase.todoapp.domain.entities.todo;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;


public class TodoFactoryTest
{
    @Test
    public void testCreateTodo()
    {
        UUID todoListId = UUID.randomUUID();
        String name = "Test Todo";
        String description = "This is a test todo";
        LocalDate dueDate = LocalDate.now().plusDays(7);
        LocalDate reminderDate = LocalDate.now().plusDays(5);

        Todo todoList = TodoFactory.createTodo(todoListId, name, description, dueDate, reminderDate);

        assertNotNull(todoList);
        assertEquals(todoListId, todoList.getTodoListId());
        assertEquals(name, todoList.getName().getValue());
        assertEquals(description, todoList.getDescription().getDescription());
        assertEquals(dueDate, todoList.getDueDate().getDate());
        assertEquals(reminderDate, todoList.getReminderDate().getDate());
        assertEquals(false, todoList.isDone());
    }


    @Test
    public void testCreateSubTodo()
    {
        UUID todoListId = UUID.randomUUID();
        UUID todoId = UUID.randomUUID();
        String name = "Test Sub Todo";
        String description = "This is a test sub todo";
        LocalDate dueDate = LocalDate.now().plusDays(7);
        LocalDate reminderDate = LocalDate.now().plusDays(5);

        Todo subTodo = TodoFactory.createSubTodo(todoListId, todoId, name, description, dueDate, reminderDate);

        assertNotNull(subTodo);
        assertEquals(todoListId, subTodo.getTodoListId());
        assertEquals(todoId, subTodo.getTodoId());
        assertEquals(name, subTodo.getName().getValue());
        assertEquals(description, subTodo.getDescription().getDescription());
        assertEquals(dueDate, subTodo.getDueDate().getDate());
        assertEquals(reminderDate, subTodo.getReminderDate().getDate());
        assertEquals(false, subTodo.isDone());
    }
}
