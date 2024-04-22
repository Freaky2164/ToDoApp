package de.dhbw.ase.todoapp.application;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.domain.todo.Name;
import de.dhbw.ase.todoapp.domain.todo.Todo;
import de.dhbw.ase.todoapp.domain.todo.TodoList;
import de.dhbw.ase.todoapp.domain.todo.TodoRepository;
import de.dhbw.ase.todoapp.domain.user.User;


@Service
public class TodoService
{
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(final TodoRepository todoRepository)
    {
        this.todoRepository = todoRepository;
    }


    public Optional<TodoList> findTodoListById(UUID todoListId)
    {
        return todoRepository.findTodoListById(todoListId);
    }


    public Optional<TodoList> findTodoListByName(Name name)
    {
        return todoRepository.findTodoListByName(name);
    }


    public List<TodoList> findTodoListsForUser(User user)
    {
        return todoRepository.findTodoListsByUserId(user.getId());
    }


    public TodoList createTodoList(User user, Name name)
    {
        TodoList todoList = new TodoList(user.getId(), name);
        return todoRepository.save(todoList);
    }


    public void deleteTodoList(TodoList list)
    {
        List<Todo> todos = todoRepository.findTodosByTodoListId(list.getId());
        todos.forEach(todoRepository::delete);
        todoRepository.delete(list);
    }


    public void renameTodoList(TodoList list, Name name)
    {
        list.changeName(name);
        todoRepository.save(list);
    }


    public Optional<Todo> findTodoById(UUID todoId)
    {
        return todoRepository.findTodoById(todoId);
    }


    public List<Todo> findFinishedTodosForUser(User user)
    {
        List<Todo> finishedTodos = new ArrayList<>();
        for (TodoList todoList : todoRepository.findTodoListsByUserId(user.getId()))
        {
            for (Todo todo : todoRepository.findTodosByTodoListId(todoList.getId()))
            {
                if (todo.isDone())
                {
                    finishedTodos.add(todo);
                }
            }
        }
        return finishedTodos;
    }


    public List<Todo> findNotFinishedTodosForUser(User user)
    {
        List<Todo> finishedTodos = new ArrayList<>();
        for (TodoList todoList : todoRepository.findTodoListsByUserId(user.getId()))
        {
            for (Todo todo : todoRepository.findTodosByTodoListId(todoList.getId()))
            {
                if (!todo.isDone())
                {
                    finishedTodos.add(todo);
                }
            }
        }
        return finishedTodos;
    }


    public Todo createTodo(Todo todo)
    {
        return todoRepository.save(todo);
    }


    public void deleteTodo(Todo todo)
    {
        List<Todo> subTodos = todoRepository.findSubTodosByTodoId(todo.getId());
        subTodos.forEach(todoRepository::delete);
        todoRepository.delete(todo);
    }


    public void setTodoAsFinished(Todo todo)
    {
        todo.setAsFinished();
        todoRepository.save(todo);
    }


    public void setTodoAsNotFinished(Todo todo)
    {
        todo.setAsNotFinished();
        todoRepository.save(todo);
    }


    public int getNumberOfFinishedTodosForUser(User user)
    {
        List<Todo> finishedTodos = findFinishedTodosForUser(user);
        return finishedTodos.size();
    }


    public int getNumberOfNotFinishedTodosForUser(User user)
    {
        List<Todo> notFinishedTodos = findNotFinishedTodosForUser(user);
        return notFinishedTodos.size();
    }
}
