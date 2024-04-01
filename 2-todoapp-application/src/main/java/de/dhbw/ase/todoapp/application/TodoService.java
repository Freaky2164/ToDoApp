package de.dhbw.ase.todoapp.application;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoList;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoListRepository;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoRepository;
import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.vo.Name;


@Service
public class TodoService
{
    private final TodoListRepository todoListRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(final TodoListRepository todoListRepository, final TodoRepository todoRepository)
    {
        this.todoListRepository = todoListRepository;
        this.todoRepository = todoRepository;
    }


    public Optional<TodoList> findTodoListById(UUID todoListId)
    {
        return todoListRepository.findById(todoListId);
    }


    public Optional<TodoList> findTodoListByName(Name name)
    {
        return todoListRepository.findByName(name);
    }


    public List<TodoList> findTodoListsForUser(User user)
    {
        return todoListRepository.findAllByUserId(user.getId());
    }


    public TodoList createTodoList(User user, Name name)
    {
        TodoList todoList = new TodoList(user.getId(), name);
        return todoListRepository.save(todoList);
    }


    public void deleteTodoList(TodoList list)
    {
        todoListRepository.delete(list);
    }


    public void renameTodoList(TodoList list, Name name)
    {
        list.changeName(name);
        todoListRepository.save(list);
    }


    public Optional<Todo> findTodoById(UUID todoId)
    {
        return todoRepository.findById(todoId);
    }


    public List<Todo> findFinishedTodosForUser(User user)
    {
        List<Todo> finishedTodos = new ArrayList<>();
        for (TodoList todoList : todoListRepository.findAllByUserId(user.getId()))
        {
            for (Todo todo : todoRepository.findAllByTodoListId(todoList.getId()))
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
        for (TodoList todoList : todoListRepository.findAllByUserId(user.getId()))
        {
            for (Todo todo : todoRepository.findAllByTodoListId(todoList.getId()))
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
