package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.entities.todo.TodoList;
import de.dhbw.ase.todoapp.domain.vo.Name;


@Repository
public interface SpringDataTodoListRepository extends JpaRepository<TodoList, UUID>
{

    Optional<TodoList> findByName(final Name name);


    List<TodoList> findAllByUserId(final UUID userId);
}
