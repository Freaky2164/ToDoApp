package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.todo.Name;
import de.dhbw.ase.todoapp.domain.todo.Todo;
import de.dhbw.ase.todoapp.domain.todo.TodoList;
import de.dhbw.ase.todoapp.domain.todo.TodoRepository;


@Repository
public class TodoRepositoryBridge implements TodoRepository
{
    @Autowired
    SpringDataTodoRepository springDataTodoRepository;

    @Autowired
    SpringDataTodoListRepository springDataTodoListRepository;

    @Override
    public Optional<TodoList> findTodoListById(UUID todoListId)
    {
        return springDataTodoListRepository.findById(todoListId);
    }


    @Override
    public Optional<TodoList> findTodoListByName(Name name)
    {
        return springDataTodoListRepository.findByName(name);
    }


    @Override
    public List<TodoList> findTodoListsByUserId(UUID userId)
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


    @Override
    public Optional<Todo> findTodoById(UUID todoId)
    {
        return springDataTodoRepository.findById(todoId);
    }


    @Override
    public Optional<Todo> findTodoByName(Name name)
    {
        return springDataTodoRepository.findByName(name);
    }


    @Override
    public Optional<Todo> findSubTodoByTodoId(UUID subTodoId)
    {
        return springDataTodoRepository.findByTodoId(subTodoId);
    }


    @Override
    public List<Todo> findTodosByTodoListId(UUID todoListId)
    {
        return springDataTodoRepository.findAllByTodoListId(todoListId);
    }


    @Override
    public Todo save(Todo todo)
    {
        return springDataTodoRepository.save(todo);
    }


    @Override
    public void delete(Todo todo)
    {
        springDataTodoRepository.delete(todo);
    }


    @Override
    public List<Todo> findSubTodosByTodoId(UUID todoId)
    {
        return springDataTodoRepository.findAllByTodoId(todoId);
    }
}
