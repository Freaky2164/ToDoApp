package de.dhbw.ase.todoapp.application;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.domain.entities.todo.Task;
import de.dhbw.ase.todoapp.domain.entities.todo.TaskRepository;
import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.vo.Name;


@Service
public class TaskService
{
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(final TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
    }


    public Task findTaskById(UUID taskId)
    {
        return this.taskRepository.findTaskById(taskId);
    }


    public List<Task> findTasksByTaskListName(Name name)
    {
        return this.taskRepository.findTasksByTaskListName(name);
    }


    public void addTaskToTaskList(Task task)
    {
        this.taskRepository.addTaskToTaskList(task);
    }


    public void removeTaskFromTaskList(Task task)
    {
        this.taskRepository.removeTaskFromTaskList(task);
    }


    public List<Task> findFinishedTasksForUser(User user)
    {
        return this.taskRepository.findFinishedTasksForUser(user);
    }


    public void setTaskAsFinished(Task task)
    {
        this.taskRepository.setTaskAsFinished(task);
    }


    public void setTaskAsNotfinished(Task task)
    {
        this.taskRepository.setTaskAsNotfinished(task);
    }


    public int getNumberOfFinishedTasksForUser(User user)
    {
        return this.taskRepository.getNumberOfFinishedTasksForUser(user);
    }


    public int getNumberOfNotFinishedTasksForUser(User user)
    {
        return this.taskRepository.getNumberOfNotFinishedTasksForUser(user);
    }
}
