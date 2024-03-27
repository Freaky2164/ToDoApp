package de.dhbw.ase.todoapp.plugins.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.dhbw.ase.todoapp.application.TodoService;
import de.dhbw.ase.todoapp.application.UserService;
import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoFactory;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoList;
import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.vo.Name;
import jakarta.servlet.http.HttpSession;


@Controller
public class TodoController
{
    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;

    @GetMapping("/todo")
    public String renderTodoPage(Model model, HttpSession session)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Map<TodoList, List<Todo>> todoMap = new LinkedHashMap<>();
        List<Todo> finishedTodos = new ArrayList<>();
        User user = userService.findUserById(userId);
        for (TodoList todoList : todoService.findTodoListsForUser(user))
        {
            todoMap.put(todoList, todoService.findNotFinishedTodosForUser(user));
        }
        finishedTodos = todoService.findFinishedTodosForUser(user);

        model.addAttribute("todoMap", todoMap);
        model.addAttribute("finishedTodos", finishedTodos);
        return "todo";
    }


    @PostMapping("/createTodoList")
    public String createTodoList(@RequestParam("todoListName") String todoListName, HttpSession session)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        User user = userService.findUserById(userId);
        todoService.createTodoList(user, new Name(todoListName));
        return "redirect:/todo";
    }


    @PostMapping("/deleteTodoList")
    public String deleteTodoList(@RequestParam("todoListId") UUID todoListId, HttpSession session)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<TodoList> todoListOptional = todoService.findTodoList(todoListId);
        todoListOptional.ifPresent(todo -> todoService.deleteTodoList(todo));
        return "redirect:/todo";
    }


    @PostMapping("/createTodo")
    public String createTodo(@RequestParam("todoListId") UUID todoListId, HttpSession session, String name, String description,
                             LocalDate dueDate, LocalDate reminderDate)
    {
        Todo todo = TodoFactory.createTodo(todoListId, name, description, dueDate, reminderDate);
        todoService.createTodo(todo);
        return "redirect:/todo";
    }


    @PostMapping("/deleteTodo")
    public String deleteTodo(@RequestParam("todoId") UUID todoId, HttpSession session)
    {
        Optional<Todo> todoOptional = todoService.findTodoBy(todoId);
        todoOptional.ifPresent(todo -> todoService.deleteTodo(todo));
        return "redirect:/todo";
    }


    @PostMapping("/markTodoAsCompleted")
    public String markTodoAsCompleted(@RequestParam("todoId") UUID todoId, HttpSession session)
    {
        Optional<Todo> todoOptional = todoService.findTodoBy(todoId);
        todoOptional.ifPresent(todo -> todoService.setTodoAsFinished(todo));
        return "redirect:/todo";
    }


    @PostMapping("/markTodoAsNotCompleted")
    public String markTodoAsNotCompleted(@RequestParam("todoId") UUID todoId, HttpSession session)
    {
        Optional<Todo> todoOptional = todoService.findTodoBy(todoId);
        todoOptional.ifPresent(todo -> todoService.setTodoAsNotFinished(todo));
        return "redirect:/todo";
    }


    @PostMapping("/createSubTodo")
    public String createSubTodo(UUID todoListId, UUID todoId, HttpSession session, String name, String description,
                                LocalDate dueDate, LocalDate reminderDate)
    {
        Todo todo = TodoFactory.createSubTodo(todoListId, todoId, name, description, dueDate, reminderDate);
        todoService.createTodo(todo);
        return "redirect:/todo";
    }
}
