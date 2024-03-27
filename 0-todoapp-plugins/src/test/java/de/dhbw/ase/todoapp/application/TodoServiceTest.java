package de.dhbw.ase.todoapp.application;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
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
import de.dhbw.ase.todoapp.domain.entities.todo.TodoList;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoListRepository;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoRepository;
import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.entities.user.UserFactory;
import de.dhbw.ase.todoapp.domain.vo.Name;


public class TodoServiceTest
{
    private TodoListRepository todoListRepository;
    private TodoRepository todoRepository;
    private TodoService todoService;

    @Before
    public void setUp()
    {
        todoListRepository = mock(TodoListRepository.class);
        todoRepository = mock(TodoRepository.class);
        todoService = new TodoService(todoListRepository, todoRepository);
    }


    @Test
    public void testFindTodoListById()
    {
        UUID id = UUID.randomUUID();
        TodoList expected = new TodoList(id, new Name("Test"));

        when(todoListRepository.findById(id)).thenReturn(Optional.of(expected));

        Optional<TodoList> result = todoService.findTodoList(id);

        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }


    @Test
    public void testCreateTodoList()
    {
        User user = UserFactory.createUser("test@example.com", "password");
        Name name = new Name("Test");
        TodoList expected = new TodoList(user.getId(), name);

        when(todoListRepository.save(any(TodoList.class))).thenReturn(expected);

        TodoList result = todoService.createTodoList(user, name);

        assertEquals(expected, result);
    }


    @Test
    public void testFindFinishedTodosForUser()
    {
        User user = UserFactory.createUser("test@example.com", "password");
        TodoList todoList = new TodoList(user.getId(), new Name("Test"));
        List<Todo> todos = new ArrayList<>();
        Todo todo1 = TodoFactory.createTodo(todoList.getId(), "Todo2", "Todo3 Description", LocalDate.now().plusDays(2), LocalDate.now());
        Todo todo2 = TodoFactory.createTodo(todoList.getId(), "Todo2", "Todo3 Description", LocalDate.now().plusDays(2), LocalDate.now());
        Todo todo3 = TodoFactory.createTodo(todoList.getId(), "Todo2", "Todo3 Description", LocalDate.now().plusDays(2), LocalDate.now());
        todo1.setAsFinished();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);

        when(todoListRepository.findAllByUserId(user.getId())).thenReturn(List.of(todoList));
        when(todoRepository.findAllByTodoListId(todoList.getId())).thenReturn(todos);

        List<Todo> result = todoService.findFinishedTodosForUser(user);

        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(Todo::isDone));
    }


    @Test
    public void testSetTodoAsFinished()
    {
        Todo todo = TodoFactory.createTodo(UUID.randomUUID(), "Todo1", "Todo1 Description", LocalDate.now().plusDays(2), LocalDate.now());

        todoService.setTodoAsFinished(todo);

        assertTrue(todo.isDone());
        verify(todoRepository).save(todo);
    }


    @Test
    public void testSetTodoAsNotfinished()
    {
        Todo todo = TodoFactory.createTodo(UUID.randomUUID(), "Todo1", "Todo1 Description", LocalDate.now().plusDays(2), LocalDate.now());

        todoService.setTodoAsNotFinished(todo);

        assertFalse(todo.isDone());
        verify(todoRepository).save(todo);
    }


    @Test
    public void testGetNumberOfFinishedTodosForUser()
    {
        User user = UserFactory.createUser("test@example.com", "password");
        TodoList todoList = new TodoList(user.getId(), new Name("Test"));
        List<Todo> todos = new ArrayList<>();
        Todo todo1 = TodoFactory.createTodo(todoList.getId(), "Todo2", "Todo3 Description", LocalDate.now().plusDays(2), LocalDate.now());
        Todo todo2 = TodoFactory.createTodo(todoList.getId(), "Todo2", "Todo3 Description", LocalDate.now().plusDays(2), LocalDate.now());
        Todo todo3 = TodoFactory.createTodo(todoList.getId(), "Todo2", "Todo3 Description", LocalDate.now().plusDays(2), LocalDate.now());
        todo1.setAsFinished();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);

        when(todoListRepository.findAllByUserId(user.getId())).thenReturn(List.of(todoList));
        when(todoRepository.findAllByTodoListId(todoList.getId())).thenReturn(todos);

        int result = todoService.getNumberOfFinishedTodosForUser(user);

        assertEquals(1, result);
    }


    @Test
    public void testGetNumberOfNotFinishedTodosForUser()
    {
        User user = UserFactory.createUser("test@example.com", "password");
        TodoList todoList = new TodoList(user.getId(), new Name("Test"));
        List<Todo> todos = new ArrayList<>();
        Todo todo1 = TodoFactory.createTodo(todoList.getId(), "Todo2", "Todo3 Description", LocalDate.now().plusDays(2), LocalDate.now());
        Todo todo2 = TodoFactory.createTodo(todoList.getId(), "Todo2", "Todo3 Description", LocalDate.now().plusDays(2), LocalDate.now());
        Todo todo3 = TodoFactory.createTodo(todoList.getId(), "Todo2", "Todo3 Description", LocalDate.now().plusDays(2), LocalDate.now());
        todo1.setAsFinished();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);

        when(todoListRepository.findAllByUserId(user.getId())).thenReturn(List.of(todoList));
        when(todoRepository.findAllByTodoListId(todoList.getId())).thenReturn(todos);

        int result = todoService.getNumberOfNotFinishedTodosForUser(user);

        assertEquals(2, result);
    }

}
