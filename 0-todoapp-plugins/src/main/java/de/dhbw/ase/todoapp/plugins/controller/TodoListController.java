package de.dhbw.ase.todoapp.plugins.controller;


import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.dhbw.ase.todoapp.domain.entities.todo.TodoList;
import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.vo.Name;
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
        notifyObservers("Es wurde eine neue To-Do-Liste \\\"" + todoListName + "\\\" erstellt!");
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
            notifyObservers("Die To-Do-Liste \\\"" + todoList.getName() + "\\\" wurde gelöscht!");
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
            Name oldName = todoList.getName();
            todoService.renameTodoList(todoList, new Name(newName));
            notifyObservers("Die To-Do-Liste \\\"" + oldName.toString() + "\\\" wurde in \\\"" + newName + "\\\" umbenannt!");
        });
        return "redirect:/todo";
    }
}
