package de.dhbw.ase.todoapp.plugins.reminder;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.application.NotificationService;
import de.dhbw.ase.todoapp.application.TodoService;
import de.dhbw.ase.todoapp.application.UserService;
import de.dhbw.ase.todoapp.domain.entities.notification.reminder.DueDateStrategy;
import de.dhbw.ase.todoapp.domain.entities.notification.reminder.ReminderDateStrategy;
import de.dhbw.ase.todoapp.domain.entities.notification.reminder.ReminderStrategy;
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
        List<ReminderStrategy> reminderStrategies = getReminderStrategies();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> checkTodoDates(reminderStrategies), 0, PERIODIC_DELAY_HOURS, TimeUnit.HOURS);
    }


    private List<ReminderStrategy> getReminderStrategies()
    {
        List<ReminderStrategy> reminderStrategies = new ArrayList<>();
        reminderStrategies.add(new DueDateStrategy());
        reminderStrategies.add(new ReminderDateStrategy());
        return reminderStrategies;
    }


    public void checkTodoDates(List<ReminderStrategy> reminderStrategies)
    {
        for (User user : userService.findAllUsers())
        {
            for (Todo todo : todoService.findNotFinishedTodosForUser(user))
            {
                reminderStrategies.forEach(reminderStrategy -> reminderStrategy.checkDate(todo, notificationService.findAllForUser(user)));
            }
        }
    }
}
