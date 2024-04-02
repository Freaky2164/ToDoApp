package de.dhbw.ase.todoapp.plugins.reminder;


import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.application.NotificationService;
import de.dhbw.ase.todoapp.application.TodoService;
import de.dhbw.ase.todoapp.application.UserService;
import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.entities.user.User;


@Service
public class TodoDateChecker
{
    private static final int PERIODIC_DELAY_HOURS = 12;

    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    public TodoDateChecker()
    {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::checkTodoDates, 0, PERIODIC_DELAY_HOURS, TimeUnit.HOURS);
    }


    public void checkTodoDates()
    {
        for (User user : userService.findAllUsers())
        {
            for (Todo todo : todoService.findNotFinishedTodosForUser(user))
            {
                LocalDate date = LocalDate.now();
                if (date.isAfter(todo.getReminderDate().getDate()))
                {
                    for (Notification notification : notificationService.findAllForUser(user))
                    {
                        notification.notify("Erinnerung: Das To-Do \\\"" + todo.getName() + "\\\" muss bis zum "
                                            + todo.getDueDate().formatDate() + " abgeschlossen werden!");
                    }
                }
                if (date.isAfter(todo.getDueDate().getDate()))
                {
                    for (Notification notification : notificationService.findAllForUser(user))
                    {
                        notification.notify("Das To-Do \\\"" + todo.getName() + "\\\" wurde nicht zum eingetragenen Datum, dem "
                                            + todo.getDueDate().formatDate() + ", abgeschlossen!");
                    }
                }
            }
        }
    }
}
