package de.dhbw.ase.todoapp.plugins.controller;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoList;
import de.dhbw.ase.todoapp.domain.entities.user.User;
import jakarta.servlet.http.HttpSession;


@Controller
public class ViewController extends BaseController
{
    @GetMapping("/todo")
    public final String renderTodoPage(HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        User user = userService.findUserById(userId);
        Map<TodoList, List<Todo>> todoMap = new LinkedHashMap<>();
        for (TodoList todoList : todoService.findTodoListsForUser(user))
        {
            todoMap.put(todoList, todoService.findNotFinishedTodosForUser(user));
        }
        List<Todo> finishedTodos = todoService.findFinishedTodosForUser(user);
        List<Notification> notifications = notificationService.findAllForUser(user);

        model.addAttribute("todoMap", todoMap);
        model.addAttribute("finishedTodos", finishedTodos);
        model.addAttribute("notifications", notifications);
        return "todo";
    }
}
