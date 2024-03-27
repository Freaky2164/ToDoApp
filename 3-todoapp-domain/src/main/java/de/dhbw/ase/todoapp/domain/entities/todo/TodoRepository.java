package de.dhbw.ase.todoapp.domain.entities.todo;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.vo.Name;


public interface TodoRepository
{
    Optional<Todo> findById(final UUID todoId);


    Optional<Todo> findByTodoId(final UUID todoId);


    Optional<Todo> findByName(final Name name);


    List<Todo> findAllByTodoListId(final UUID todoListId);


    Todo save(final Todo todo);


    void delete(final Todo todo);
}
