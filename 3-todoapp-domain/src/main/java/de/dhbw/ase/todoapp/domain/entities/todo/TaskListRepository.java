package de.dhbw.ase.todoapp.domain.entities.todo;


import java.util.List;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.vo.Name;


public interface TaskListRepository
{
    public TaskList findTaskListById(final UUID taskListId);


    public TaskList findTaskListByName(final Name name);


    public List<TaskList> findTaskListsForUser(final UUID user);


    public void createNewTaskList(final Name name);


    public void createNewTaskListForUser(final Name name, final User user);


    public void deleteTaskList(final TaskList taskList);


    public void renameTaskList(final UUID taskListId, final Name name);
}
