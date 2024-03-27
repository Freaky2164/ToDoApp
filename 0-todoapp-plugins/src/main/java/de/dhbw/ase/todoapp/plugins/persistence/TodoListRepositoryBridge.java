package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.entities.todo.TodoList;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoListRepository;
import de.dhbw.ase.todoapp.domain.vo.Name;


@Repository
public class TodoListRepositoryBridge implements TodoListRepository
{

    @Autowired
    SpringDataTodoListRepository springDataTodoListRepository;

    @Override
    public Optional<TodoList> findById(UUID todoListId)
    {
        return springDataTodoListRepository.findById(todoListId);
    }


    @Override
    public Optional<TodoList> findByName(Name name)
    {
        return springDataTodoListRepository.findByName(name);
    }


    @Override
    public List<TodoList> findAllByUserId(UUID userId)
    {
        return springDataTodoListRepository.findAllByUserId(userId);
    }


    @Override
    public TodoList save(TodoList todoList)
    {
        return springDataTodoListRepository.save(todoList);
    }


    @Override
    public void delete(TodoList todoList)
    {
        springDataTodoListRepository.delete(todoList);
    }
}
