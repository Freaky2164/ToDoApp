package de.dhbw.ase.todoapp.plugins.controller;


import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.dhbw.ase.todoapp.abstraction.event.TodoListCreatedEvent;
import de.dhbw.ase.todoapp.abstraction.event.TodoListDeletedEvent;
import de.dhbw.ase.todoapp.abstraction.event.TodoListRenamedEvent;
import de.dhbw.ase.todoapp.domain.todo.Name;
import de.dhbw.ase.todoapp.domain.todo.TodoList;
import de.dhbw.ase.todoapp.domain.user.User;
import jakarta.servlet.http.HttpSession;


@Controller
public class TodoListController extends BaseController
{
    @PostMapping("/createTodoList")
    public String createTodoList(@RequestParam("todoListName") String todoListName, HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        User user = userService.findUserById(userId);
        todoService.createTodoList(user, new Name(todoListName));
        notifyObservers(new TodoListCreatedEvent(todoListName));
        return "redirect:/todo";
    }


    @PostMapping("/deleteTodoList")
    public String deleteTodoList(@RequestParam("todoListId") UUID todoListId, HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<TodoList> todoListOptional = todoService.findTodoListById(todoListId);
        todoListOptional.ifPresent(todoList ->
        {
            todoService.deleteTodoList(todoList);
            notifyObservers(new TodoListDeletedEvent(todoList.getName().getValue()));
        });
        return "redirect:/todo";
    }


    @PostMapping("/renameTodoList")
    public String renameTodoList(@RequestParam("todoListId") UUID todoListId, @RequestParam("newName") String newName,
                                 HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<TodoList> todoListOptional = todoService.findTodoListById(todoListId);
        todoListOptional.ifPresent(todoList ->
        {
            todoService.renameTodoList(todoList, new Name(newName));
            notifyObservers(new TodoListRenamedEvent(todoList.getName().getValue(), newName));
        });
        return "redirect:/todo";
    }
}
