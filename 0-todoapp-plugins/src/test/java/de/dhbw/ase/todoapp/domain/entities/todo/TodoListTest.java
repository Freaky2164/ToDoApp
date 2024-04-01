package de.dhbw.ase.todoapp.domain.entities.todo;


import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.vo.Name;


public class TodoListTest
{
    private UUID userId;
    private Name name;

    @Before
    public void setUp()
    {
        userId = UUID.randomUUID();
        name = new Name("Todo List Name");
    }


    @Test
    public void testTodoListCreation()
    {
        TodoList todoList = new TodoList(userId, name);
        assertNotNull(todoList.getId());
        assertEquals(userId, todoList.getUserId());
        assertEquals(name, todoList.getName());
    }


    @Test(expected = NullPointerException.class)
    public void testTodoListCreationWithNullUserId()
    {
        new TodoList(null, name);
    }


    @Test(expected = NullPointerException.class)
    public void testTodoListCreationWithNullName()
    {
        new TodoList(userId, null);
    }


    @Test
    public void testChangeName()
    {
        TodoList todoList = new TodoList(userId, name);
        Name newName = new Name("New Todo List Name");
        todoList.changeName(newName);
        assertEquals(newName, todoList.getName());
    }


    @Test(expected = NullPointerException.class)
    public void testChangeNameWithNullName()
    {
        TodoList todoList = new TodoList(userId, name);
        todoList.changeName(null);
    }
}
