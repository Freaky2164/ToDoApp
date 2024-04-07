package de.dhbw.ase.todoapp.plugins.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoFactory;
import de.dhbw.ase.todoapp.domain.vo.CalendarDate;
import de.dhbw.ase.todoapp.domain.vo.Description;
import de.dhbw.ase.todoapp.domain.vo.Name;
import jakarta.servlet.http.HttpSession;


@Controller
public class TodoController extends ViewController
{
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
}
