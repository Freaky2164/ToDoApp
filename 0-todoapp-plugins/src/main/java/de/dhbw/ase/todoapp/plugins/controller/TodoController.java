package de.dhbw.ase.todoapp.plugins.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.dhbw.ase.todoapp.abstraction.event.SubTodoCreatedEvent;
import de.dhbw.ase.todoapp.abstraction.event.TodoCreatedEvent;
import de.dhbw.ase.todoapp.abstraction.event.TodoDeletedEvent;
import de.dhbw.ase.todoapp.abstraction.event.TodoEditedEvent;
import de.dhbw.ase.todoapp.abstraction.event.TodoMarkedAsCompletedEvent;
import de.dhbw.ase.todoapp.abstraction.event.TodoMarkedAsNotCompletedEvent;
import de.dhbw.ase.todoapp.domain.todo.CalendarDate;
import de.dhbw.ase.todoapp.domain.todo.Description;
import de.dhbw.ase.todoapp.domain.todo.Name;
import de.dhbw.ase.todoapp.domain.todo.Todo;
import de.dhbw.ase.todoapp.domain.todo.TodoFactory;
import jakarta.servlet.http.HttpSession;


@Controller
public class TodoController extends BaseController
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
        notifyObservers(new TodoCreatedEvent(name));
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
            notifyObservers(new TodoDeletedEvent(todo.getName().getValue()));
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
            notifyObservers(new TodoEditedEvent(name));
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
            notifyObservers(new TodoMarkedAsCompletedEvent(todo.getName().getValue(), todo.getCompletionDate().format(pattern)));
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
            notifyObservers(new TodoMarkedAsNotCompletedEvent(todo.getName().getValue()));
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
        notifyObservers(new SubTodoCreatedEvent(name));
        return "redirect:/todo";
    }
}
