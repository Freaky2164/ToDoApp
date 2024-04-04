package de.dhbw.ase.todoapp.plugins.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import de.dhbw.ase.todoapp.abstraction.observer.Observable;
import de.dhbw.ase.todoapp.application.NotificationService;
import de.dhbw.ase.todoapp.application.TodoService;
import de.dhbw.ase.todoapp.application.UserService;
import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoFactory;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoList;
import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.vo.CalendarDate;
import de.dhbw.ase.todoapp.domain.vo.Description;
import de.dhbw.ase.todoapp.domain.vo.Name;
import de.dhbw.ase.todoapp.domain.vo.WebHook;
import jakarta.servlet.http.HttpSession;


@Controller
public class TodoController extends Observable
{
    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/todo")
    public String renderTodoPage(HttpSession session, Model model)
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


    @PostMapping("/createTodo")
    public String createTodo(@RequestParam("todoListId") UUID todoListId, @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("dueDate") LocalDate dueDate, @RequestParam("reminderDate") LocalDate reminderDate,
                             HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Todo todo = TodoFactory.createTodo(todoListId, name, description, dueDate, reminderDate);
        todoService.createTodo(todo);
        notifyObservers("Es wurde ein neues To-Do \\\"" + name + "\\\" erstellt!");
        return "redirect:/todo";
    }


    @PostMapping("/deleteTodo")
    public String deleteTodo(@RequestParam("todoId") UUID todoId, HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<Todo> todoOptional = todoService.findTodoById(todoId);
        todoOptional.ifPresent(todo ->
        {
            todoService.deleteTodo(todo);
            notifyObservers("Das To-Do \\\"" + todo.getName() + "\\\" wurde am gelöscht!");
        });
        return "redirect:/todo";
    }


    @PostMapping("/editTodo")
    public String editTodo(@RequestParam("todoId") UUID todoId, @RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("dueDate") LocalDate dueDate, @RequestParam("reminderDate") LocalDate reminderDate,
                           HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<Todo> optionalTodo = todoService.findTodoById(todoId);
        optionalTodo.ifPresent(todo ->
        {
            todo.setName(new Name(name));
            todo.setDescription(new Description(description));
            todo.setDueDate(new CalendarDate(dueDate));
            todo.setReminderDate(new CalendarDate(reminderDate));
            todoService.createTodo(todo);
            notifyObservers("Das To-Do \\\"" + name + "\\\" wurde bearbeitet!");
        });
        return "redirect:/todo";
    }


    @PostMapping("/markTodoAsCompleted")
    public String markTodoAsCompleted(@RequestParam("todoId") UUID todoId, HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<Todo> todoOptional = todoService.findTodoById(todoId);
        todoOptional.ifPresent(todo ->
        {
            todoService.setTodoAsFinished(todo);
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            notifyObservers("Das To-Do \\\"" + todo.getName() + "\\\" wurde am " + todo.getCompletionDate().format(pattern)
                            + " abgeschlossen!");
        });
        return "redirect:/todo";
    }


    @PostMapping("/markTodoAsNotCompleted")
    public String markTodoAsNotCompleted(@RequestParam("todoId") UUID todoId, HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<Todo> todoOptional = todoService.findTodoById(todoId);
        todoOptional.ifPresent(todo ->
        {
            todoService.setTodoAsNotFinished(todo);
            notifyObservers("Das To-Do \\\"" + todo.getName() + "\\\" ist nicht länger abgeschlossen!");
        });
        return "redirect:/todo";
    }


    @PostMapping("/createSubTodo")
    public String createSubTodo(@RequestParam("todoListId") UUID todoListId, @RequestParam("todoId") UUID todoId,
                                @RequestParam("name") String name, @RequestParam("description") String description,
                                @RequestParam("dueDate") LocalDate dueDate, @RequestParam("reminderDate") LocalDate reminderDate,
                                HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Todo todo = TodoFactory.createSubTodo(todoListId, todoId, name, description, dueDate, reminderDate);
        todoService.createTodo(todo);
        notifyObservers("Es wurde ein neues Sub-To-Do \\\"" + name + "\\\" erstellt!");
        return "redirect:/todo";
    }


    @PostMapping("/createNotification")
    public String createNotification(@RequestParam("name") String name, @RequestParam("webHookUrl") String webHookUrl,
                                     HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Notification notification = new Notification(userId, new Name(name), new WebHook(webHookUrl));
        notificationService.createNotification(notification);
        registerObserver(notification);
        return "redirect:/todo";
    }


    @PostMapping("/deleteNotification")
    public String deleteNotification(@RequestParam("notificationId") UUID notificationId, HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<Notification> notificationOptional = notificationService.findById(notificationId);
        notificationOptional.ifPresent(notification ->
        {
            notificationService.deleteNotification(notification);
            unregisterObserver(notification);
        });
        return "redirect:/todo";
    }


    @PostMapping("/editNotification")
    public String editNotification(@RequestParam("notificationId") UUID notificationId, @RequestParam("name") String name,
                                   @RequestParam("webHookUrl") String webHookUrl, HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<Notification> notificationOptional = notificationService.findById(notificationId);
        notificationOptional.ifPresent(notification ->
        {
            notification.changeName(new Name(name));
            notification.setWebHook(new WebHook(webHookUrl));
            notificationService.createNotification(notification);
        });
        return "redirect:/todo";
    }
}
