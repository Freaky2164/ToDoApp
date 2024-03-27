package de.dhbw.ase.todoapp.plugins.persistence;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.entities.todo.TodoList;
import de.dhbw.ase.todoapp.domain.vo.Name;


public class TodoListRepositoryBridgeTest
{

    private TodoListRepositoryBridge todoListRepositoryBridge;
    private SpringDataTodoListRepository springDataTodoListRepository;

    @Before
    public void setUp()
    {
        springDataTodoListRepository = mock(SpringDataTodoListRepository.class);
        todoListRepositoryBridge = new TodoListRepositoryBridge();
        todoListRepositoryBridge.springDataTodoListRepository = springDataTodoListRepository;
    }


    @Test
    public void testFindById()
    {
        UUID todoListId = UUID.randomUUID();
        TodoList expectedTodoList = new TodoList(todoListId, new Name("Test TodoList"));
        when(springDataTodoListRepository.findById(todoListId)).thenReturn(Optional.of(expectedTodoList));

        Optional<TodoList> result = todoListRepositoryBridge.findById(todoListId);

        assertTrue(result.isPresent());
        assertEquals(expectedTodoList, result.get());
    }


    @Test
    public void testFindByName()
    {
        Name name = new Name("Test TodoList");
        TodoList expectedTodoList = new TodoList(UUID.randomUUID(), name);
        when(springDataTodoListRepository.findByName(name)).thenReturn(Optional.of(expectedTodoList));

        Optional<TodoList> result = todoListRepositoryBridge.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(expectedTodoList, result.get());
    }


    @Test
    public void testFindAllByUserId()
    {
        UUID userId = UUID.randomUUID();
        List<TodoList> expectedTodoLists = new ArrayList<>();
        expectedTodoLists.add(new TodoList(UUID.randomUUID(), new Name("TodoList 1")));
        expectedTodoLists.add(new TodoList(UUID.randomUUID(), new Name("TodoList 2")));
        when(springDataTodoListRepository.findAllByUserId(userId)).thenReturn(expectedTodoLists);

        List<TodoList> result = todoListRepositoryBridge.findAllByUserId(userId);

        assertEquals(expectedTodoLists, result);
    }


    @Test
    public void testSave()
    {
        TodoList todoListToSave = new TodoList(UUID.randomUUID(), new Name("Test TodoList"));
        TodoList savedTodoList = new TodoList(todoListToSave.getId(), todoListToSave.getName());
        when(springDataTodoListRepository.save(todoListToSave)).thenReturn(savedTodoList);

        TodoList result = todoListRepositoryBridge.save(todoListToSave);

        assertEquals(savedTodoList, result);
    }


    @Test
    public void testDelete()
    {
        TodoList todoListToDelete = new TodoList(UUID.randomUUID(), new Name("Test TodoList"));

        todoListRepositoryBridge.delete(todoListToDelete);

        verify(springDataTodoListRepository).delete(todoListToDelete);
    }
}
