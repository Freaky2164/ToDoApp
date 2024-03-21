package de.dhbw.ase.todoapp.domain.entities.todo;


import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.vo.Name;


public class TaskListTest
{

    private UUID userId;
    private Name name;

    @Before
    public void setUp()
    {
        userId = UUID.randomUUID();
        name = new Name("Task List Name");
    }


    @Test
    public void testTaskListCreation()
    {
        TaskList taskList = new TaskList(userId, name);
        assertNotNull(taskList.getId());
        assertEquals(userId, taskList.getUserId());
        assertEquals(name, taskList.getName());
    }


    @Test(expected = NullPointerException.class)
    public void testTaskListCreationWithNullUserId()
    {
        new TaskList(null, name);
    }


    @Test(expected = NullPointerException.class)
    public void testTaskListCreationWithNullName()
    {
        new TaskList(userId, null);
    }


    @Test
    public void testChangeName()
    {
        TaskList taskList = new TaskList(userId, name);
        Name newName = new Name("New Task List Name");
        taskList.changeName(newName);
        assertEquals(newName, taskList.getName());
    }


    @Test(expected = NullPointerException.class)
    public void testChangeNameWithNullName()
    {
        TaskList taskList = new TaskList(userId, name);
        taskList.changeName(null);
    }
}
