package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoRepository;
import de.dhbw.ase.todoapp.domain.vo.Name;


@Repository
public class TodoRepositoryBridge implements TodoRepository
{
    @Autowired
    SpringDataTodoRepository springDataTodoRepository;

    @Override
    public Optional<Todo> findById(UUID todoId)
    {
        return springDataTodoRepository.findById(todoId);
    }


    @Override
    public Optional<Todo> findByTodoId(UUID subTodoId)
    {
        return springDataTodoRepository.findByTodoId(subTodoId);
    }


    @Override
    public Optional<Todo> findByName(Name name)
    {
        return springDataTodoRepository.findByName(name);
    }


    @Override
    public List<Todo> findAllByTodoListId(UUID todoListId)
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
    public List<Todo> findAllSubTodoByTodoId(UUID todoId)
    {
        return springDataTodoRepository.findAllByTodoId(todoId);
    }
}
