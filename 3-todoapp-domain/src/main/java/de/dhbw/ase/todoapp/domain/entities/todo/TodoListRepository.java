package de.dhbw.ase.todoapp.domain.entities.todo;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.vo.Name;


public interface TodoListRepository
{
    Optional<TodoList> findById(final UUID todoListId);


    Optional<TodoList> findByName(final Name name);


    List<TodoList> findAllByUserId(final UUID userId);


    TodoList save(final TodoList todoList);


    void delete(final TodoList todoList);
}
