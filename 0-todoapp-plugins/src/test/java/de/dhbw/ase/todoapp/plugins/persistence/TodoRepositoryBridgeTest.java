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

import de.dhbw.ase.todoapp.domain.todo.Name;
import de.dhbw.ase.todoapp.domain.todo.Todo;
import de.dhbw.ase.todoapp.domain.todo.TodoFactory;
import de.dhbw.ase.todoapp.domain.todo.TodoList;


public class TodoRepositoryBridgeTest
{
    private TodoRepositoryBridge todoRepositoryBridge;
    private SpringDataTodoRepository springDataTodoRepository;
    private SpringDataTodoListRepository springDataTodoListRepository;

    @Before
    public void setUp()
    {
        springDataTodoListRepository = mock(SpringDataTodoListRepository.class);
        springDataTodoRepository = mock(SpringDataTodoRepository.class);
        todoRepositoryBridge = new TodoRepositoryBridge();
        todoRepositoryBridge.springDataTodoListRepository = springDataTodoListRepository;
        todoRepositoryBridge.springDataTodoRepository = springDataTodoRepository;
    }


    @Test
    public void findTodoListById()
    {
        UUID todoListId = UUID.randomUUID();
        TodoList expectedTodoList = new TodoList(todoListId, new Name("Test TodoList"));
        when(springDataTodoListRepository.findById(todoListId)).thenReturn(Optional.of(expectedTodoList));

        Optional<TodoList> result = todoRepositoryBridge.findTodoListById(todoListId);

        assertTrue(result.isPresent());
        assertEquals(expectedTodoList, result.get());
    }


    @Test
    public void testFindTodoListByName()
    {
        Name name = new Name("Test TodoList");
        TodoList expectedTodoList = new TodoList(UUID.randomUUID(), name);
        when(springDataTodoListRepository.findByName(name)).thenReturn(Optional.of(expectedTodoList));

        Optional<TodoList> result = todoRepositoryBridge.findTodoListByName(name);

        assertTrue(result.isPresent());
        assertEquals(expectedTodoList, result.get());
    }


    @Test
    public void testFindTodoListsByUserId()
    {
        UUID userId = UUID.randomUUID();
        List<TodoList> expectedTodoLists = new ArrayList<>();
        expectedTodoLists.add(new TodoList(UUID.randomUUID(), new Name("TodoList 1")));
        expectedTodoLists.add(new TodoList(UUID.randomUUID(), new Name("TodoList 2")));
        when(springDataTodoListRepository.findAllByUserId(userId)).thenReturn(expectedTodoLists);

        List<TodoList> result = todoRepositoryBridge.findTodoListsByUserId(userId);

        assertEquals(expectedTodoLists, result);
    }


    @Test
    public void testSaveTodoList()
    {
        TodoList todoListToSave = new TodoList(UUID.randomUUID(), new Name("Test TodoList"));
        TodoList savedTodoList = new TodoList(todoListToSave.getId(), todoListToSave.getName());
        when(springDataTodoListRepository.save(todoListToSave)).thenReturn(savedTodoList);

        TodoList result = todoRepositoryBridge.save(todoListToSave);

        assertEquals(savedTodoList, result);
    }


    @Test
    public void testDeleteTodoList()
    {
        TodoList todoListToDelete = new TodoList(UUID.randomUUID(), new Name("Test TodoList"));

        todoRepositoryBridge.delete(todoListToDelete);

        verify(springDataTodoListRepository).delete(todoListToDelete);
    }


    @Test
    public void testFindTodoById()
    {
        UUID todoId = UUID.randomUUID();
        Todo expectedTodo = TodoFactory.createTodo(UUID.randomUUID(), "Test Todo", "Test Todo Description", LocalDate.now().plusDays(2),
                                                   LocalDate.now());
        when(springDataTodoRepository.findById(todoId)).thenReturn(Optional.of(expectedTodo));

        Optional<Todo> result = todoRepositoryBridge.findTodoById(todoId);

        assertTrue(result.isPresent());
        assertEquals(expectedTodo, result.get());
    }


    @Test
    public void testFindTodoByName()
    {
        Name name = new Name("Test Todo");
        Todo expectedTodo = TodoFactory.createTodo(UUID.randomUUID(), "Test Todo", "Test Todo Description", LocalDate.now().plusDays(2),
                                                   LocalDate.now());
        when(springDataTodoRepository.findByName(name)).thenReturn(Optional.of(expectedTodo));

        Optional<Todo> result = todoRepositoryBridge.findTodoByName(name);

        assertTrue(result.isPresent());
        assertEquals(expectedTodo, result.get());
    }


    @Test
    public void testFindSubTodoByTodoId()
    {
        UUID todoId = UUID.randomUUID();
        Todo expectedTodo = TodoFactory.createTodo(UUID.randomUUID(), "Test Todo", "Test Todo Description", LocalDate.now().plusDays(2),
                                                   LocalDate.now());
        when(springDataTodoRepository.findByTodoId(todoId)).thenReturn(Optional.of(expectedTodo));

        Optional<Todo> result = todoRepositoryBridge.findSubTodoByTodoId(todoId);

        assertTrue(result.isPresent());
        assertEquals(expectedTodo, result.get());
    }


    @Test
    public void testFindTodosByTodoListId()
    {
        UUID todoListId = UUID.randomUUID();
        List<Todo> expectedTodos = new ArrayList<>();
        expectedTodos.add(TodoFactory.createTodo(UUID.randomUUID(), "Todo1", "Todo1 Description", LocalDate.now().plusDays(2),
                                                 LocalDate.now()));
        expectedTodos.add(TodoFactory.createTodo(UUID.randomUUID(), "Todo1", "Todo1 Description", LocalDate.now().plusDays(2),
                                                 LocalDate.now()));
        when(springDataTodoRepository.findAllByTodoListId(todoListId)).thenReturn(expectedTodos);

        List<Todo> result = todoRepositoryBridge.findTodosByTodoListId(todoListId);
        assertEquals(expectedTodos, result);
    }


    @Test
    public void testSaveTodo()
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
    public void testDeleteTodo()
    {
        Todo todoToDelete = TodoFactory.createTodo(UUID.randomUUID(), "Test Todo", "Test Todo Description", LocalDate.now().plusDays(2),
                                                   LocalDate.now());

        todoRepositoryBridge.delete(todoToDelete);

        verify(springDataTodoRepository).delete(todoToDelete);
    }
}
