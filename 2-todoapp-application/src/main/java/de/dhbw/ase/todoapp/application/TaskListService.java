package de.dhbw.ase.todoapp.application;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.domain.entities.todo.TaskList;
import de.dhbw.ase.todoapp.domain.entities.todo.TaskListRepository;
import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.vo.Name;


@Service
public class TaskListService
{
    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskListService(final TaskListRepository taskListRepository)
    {
        this.taskListRepository = taskListRepository;
    }


    public TaskList findTaskList(UUID taskListId)
    {
        return this.taskListRepository.findTaskListById(taskListId);
    }


    public TaskList findTaskList(Name name)
    {
        return this.taskListRepository.findTaskListByName(name);
    }


    public List<TaskList> findTaskListsForUser(User user)
    {
        return this.taskListRepository.findTaskListsForUser(user != null ? user.getId() : null);
    }


    public void createTaskList(Name name, User user)
    {
        this.taskListRepository.createNewTaskListForUser(name, user);
    }


    public void deleteTaskList(TaskList list)
    {
        this.taskListRepository.deleteTaskList(list);
    }


    public void renameTaskList(TaskList list, Name name)
    {
        this.taskListRepository.renameTaskList(list.getId(), name);
    }
}
