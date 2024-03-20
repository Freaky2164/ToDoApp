package de.dhbw.ase.todoapp.domain.entities.todo;


import java.util.List;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.vo.Name;


public interface TaskRepository
{
    public Task findTaskById(UUID taskId);


    public List<Task> findTasksByTaskListName(Name name);


    public void addTaskToTaskList(final Task task);


    public void removeTaskFromTaskList(final Task task);


    public List<Task> findFinishedTasksForUser(final User user);


    public void setTaskAsFinished(final Task task);


    public void setTaskAsNotfinished(final Task task);


    public int getNumberOfFinishedTasksForUser(final User user);


    public int getNumberOfNotFinishedTasksForUser(final User user);
}
