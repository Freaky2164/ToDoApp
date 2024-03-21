package de.dhbw.ase.todoapp.domain.entities.todo;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;


public class TaskFactoryTest
{

    @Test
    public void testCreateTask()
    {
        UUID taskListId = UUID.randomUUID();
        String name = "Test Task";
        String description = "This is a test task";
        LocalDate dueDate = LocalDate.now().plusDays(7);
        LocalDate reminderDate = LocalDate.now().plusDays(5);

        Task task = TaskFactory.createTask(taskListId, name, description, dueDate, reminderDate);

        assertNotNull(task);
        assertEquals(taskListId, task.getTaskListId());
        assertEquals(name, task.getName().getName());
        assertEquals(description, task.getDescription().getDescription());
        assertEquals(dueDate, task.getDueDate().getDate());
        assertEquals(reminderDate, task.getReminderDate().getDate());
        assertEquals(false, task.isDone());
    }


    @Test
    public void testCreateSubTask()
    {
        UUID taskListId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        String name = "Test SubTask";
        String description = "This is a test subtask";
        LocalDate dueDate = LocalDate.now().plusDays(7);
        LocalDate reminderDate = LocalDate.now().plusDays(5);

        Task subTask = TaskFactory.createSubTask(taskListId, taskId, name, description, dueDate, reminderDate);

        assertNotNull(subTask);
        assertEquals(taskListId, subTask.getTaskListId());
        assertEquals(taskId, subTask.getTaskId());
        assertEquals(name, subTask.getName().getName());
        assertEquals(description, subTask.getDescription().getDescription());
        assertEquals(dueDate, subTask.getDueDate().getDate());
        assertEquals(reminderDate, subTask.getReminderDate().getDate());
        assertEquals(false, subTask.isDone());
    }
}
