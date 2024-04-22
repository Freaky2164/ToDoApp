package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.todo.Name;
import de.dhbw.ase.todoapp.domain.todo.TodoList;


@Repository
public interface SpringDataTodoListRepository extends JpaRepository<TodoList, UUID>
{
    Optional<TodoList> findByName(final Name name);


    List<TodoList> findAllByUserId(final UUID userId);
}
