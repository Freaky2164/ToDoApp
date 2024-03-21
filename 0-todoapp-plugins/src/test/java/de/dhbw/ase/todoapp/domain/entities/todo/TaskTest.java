package de.dhbw.ase.todoapp.domain.entities.todo;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.exceptions.SubTaskNotAssignedToTaskException;
import de.dhbw.ase.todoapp.domain.exceptions.TaskNotAssignedToTaskListException;
import de.dhbw.ase.todoapp.domain.vo.CalendarDate;
import de.dhbw.ase.todoapp.domain.vo.Description;
import de.dhbw.ase.todoapp.domain.vo.Name;


public class TaskTest
{

    private UUID taskListId;
    private Name name;
    private Description description;
    private CalendarDate dueDate;
    private CalendarDate reminderDate;
    private boolean done;

    @Before
    public void setUp()
    {
        taskListId = UUID.randomUUID();
        name = new Name("Task Name");
        description = new Description("Task Description");
        dueDate = new CalendarDate(LocalDate.now().plusDays(1));
        reminderDate = new CalendarDate(LocalDate.now());
        done = false;
    }


    @Test(expected = TaskNotAssignedToTaskListException.class)
    public void testTaskCreationWithoutTaskListId()
    {
        new Task(null, name, description, dueDate, reminderDate, done);
    }


    @Test(expected = SubTaskNotAssignedToTaskException.class)
    public void testSubTaskCreationWithoutTaskId()
    {
        UUID taskId = null;
        new Task(taskListId, taskId, name, description, dueDate, reminderDate, done);
    }


    @Test
    public void testTaskCreation()
    {
        Task task = new Task(taskListId, name, description, dueDate, reminderDate, done);
        assertNotNull(task.getId());
        assertEquals(taskListId, task.getTaskListId());
        assertEquals(name, task.getName());
        assertEquals(description, task.getDescription());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(reminderDate, task.getReminderDate());
        assertEquals(done, task.isDone());
        assertNotNull(task.getCreationDate());
        assertEquals(null, task.getCompletionDate());
    }


    @Test
    public void testSetTaskAsFinished()
    {
        Task task = new Task(taskListId, name, description, dueDate, reminderDate, done);
        task.setTaskAsFinished();
        assertTrue(task.isDone());
        assertNotNull(task.getCompletionDate());
    }


    @Test
    public void testSetTaskAsNotFinished()
    {
        Task task = new Task(taskListId, name, description, dueDate, reminderDate, done);
        task.setTaskAsFinished();
        task.setTaskAsNotFinished();
        assertFalse(task.isDone());
        assertEquals(null, task.getCompletionDate());
    }
}
