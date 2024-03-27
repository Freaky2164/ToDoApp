package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.vo.Name;


@Repository
public interface SpringDataTodoRepository extends JpaRepository<Todo, UUID>
{
    Optional<Todo> findByTodoId(UUID todoId);


    Optional<Todo> findByName(Name name);


    List<Todo> findAllByTodoListId(UUID todoListId);
}
