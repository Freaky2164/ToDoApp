package de.dhbw.ase.todoapp.domain.todo;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TodoRepository
{
    Optional<TodoList> findTodoListById(final UUID todoListId);


    Optional<TodoList> findTodoListByName(final Name name);


    List<TodoList> findTodoListsByUserId(final UUID userId);


    TodoList save(final TodoList todoList);


    void delete(final TodoList todoList);


    Optional<Todo> findTodoById(final UUID todoId);


    Optional<Todo> findTodoByName(final Name name);


    Optional<Todo> findSubTodoByTodoId(final UUID subTodoId);


    List<Todo> findTodosByTodoListId(final UUID todoListId);


    List<Todo> findSubTodosByTodoId(UUID id);


    Todo save(final Todo todo);


    void delete(final Todo todo);
}
