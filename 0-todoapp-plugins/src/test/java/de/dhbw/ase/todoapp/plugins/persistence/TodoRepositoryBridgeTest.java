package de.dhbw.ase.todoapp.plugins.persistence;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoFactory;
import de.dhbw.ase.todoapp.domain.vo.Name;


public class TodoRepositoryBridgeTest
{
    private TodoRepositoryBridge todoRepositoryBridge;
    private SpringDataTodoRepository springDataTodoRepository;

    @Before
    public void setUp()
    {
        springDataTodoRepository = mock(SpringDataTodoRepository.class);
        todoRepositoryBridge = new TodoRepositoryBridge();
        todoRepositoryBridge.springDataTodoRepository = springDataTodoRepository;
    }


    @Test
    public void testFindById()
    {
        UUID todoId = UUID.randomUUID();
        Todo expectedTodo = TodoFactory.createTodo(UUID.randomUUID(), "Test Todo", "Test Todo Description", LocalDate.now().plusDays(2),
                                                   LocalDate.now());
        when(springDataTodoRepository.findById(todoId)).thenReturn(Optional.of(expectedTodo));

        Optional<Todo> result = todoRepositoryBridge.findById(todoId);

        assertTrue(result.isPresent());
        assertEquals(expectedTodo, result.get());
    }


    @Test
    public void testFindByTodoId()
    {
        UUID todoId = UUID.randomUUID();
        Todo expectedTodo = TodoFactory.createTodo(UUID.randomUUID(), "Test Todo", "Test Todo Description", LocalDate.now().plusDays(2),
                                                   LocalDate.now());
        when(springDataTodoRepository.findByTodoId(todoId)).thenReturn(Optional.of(expectedTodo));

        Optional<Todo> result = todoRepositoryBridge.findByTodoId(todoId);

        assertTrue(result.isPresent());
        assertEquals(expectedTodo, result.get());
    }


    @Test
    public void testFindByName()
    {
        Name name = new Name("Test Todo");
        Todo expectedTodo = TodoFactory.createTodo(UUID.randomUUID(), "Test Todo", "Test Todo Description", LocalDate.now().plusDays(2),
                                                   LocalDate.now());
        when(springDataTodoRepository.findByName(name)).thenReturn(Optional.of(expectedTodo));

        Optional<Todo> result = todoRepositoryBridge.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(expectedTodo, result.get());
    }


    @Test
    public void testFindAllByTodoListId()
    {
        UUID todoListId = UUID.randomUUID();
        List<Todo> expectedTodos = new ArrayList<>();
        expectedTodos.add(TodoFactory.createTodo(UUID.randomUUID(), "Todo1", "Todo1 Description", LocalDate.now().plusDays(2),
                                                 LocalDate.now()));
        expectedTodos.add(TodoFactory.createTodo(UUID.randomUUID(), "Todo1", "Todo1 Description", LocalDate.now().plusDays(2),
                                                 LocalDate.now()));
        when(springDataTodoRepository.findAllByTodoListId(todoListId)).thenReturn(expectedTodos);

        List<Todo> result = todoRepositoryBridge.findAllByTodoListId(todoListId);
        assertEquals(expectedTodos, result);
    }


    @Test
    public void testSave()
    {
        Todo todoToSave = TodoFactory.createTodo(UUID.randomUUID(), "Todo1", "Todo1 Description", LocalDate.now().plusDays(2),
                                                 LocalDate.now());
        Todo savedTodo = TodoFactory.createTodo(UUID.randomUUID(), "Todo1", "Todo1 Description", LocalDate.now().plusDays(2),
                                                LocalDate.now());
        when(springDataTodoRepository.save(todoToSave)).thenReturn(savedTodo);

        Todo result = todoRepositoryBridge.save(todoToSave);

        assertEquals(savedTodo, result);
    }


    @Test
    public void testDelete()
    {
        Todo todoToDelete = TodoFactory.createTodo(UUID.randomUUID(), "Test Todo", "Test Todo Description", LocalDate.now().plusDays(2),
                                                   LocalDate.now());

        todoRepositoryBridge.delete(todoToDelete);

        verify(springDataTodoRepository).delete(todoToDelete);
    }
}
