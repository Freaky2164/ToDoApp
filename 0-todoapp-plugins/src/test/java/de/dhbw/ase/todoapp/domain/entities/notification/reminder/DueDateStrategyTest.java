package de.dhbw.ase.todoapp.domain.entities.notification.reminder;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import de.dhbw.ase.todoapp.abstraction.event.ReachedDueDateEvent;
import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.entities.todo.Todo;
import de.dhbw.ase.todoapp.domain.entities.todo.TodoFactory;


public class DueDateStrategyTest
{
    @Test
    public void testCheckDate_NotificationSent()
    {
        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.minusDays(1);
        Todo todo = TodoFactory.createTodo(UUID.randomUUID(), "Test Todo", "Todo Description", dueDate, dueDate.minusDays(1));
        List<Notification> notifications = new ArrayList<>();
        Notification notification1 = mock(Notification.class);
        Notification notification2 = mock(Notification.class);
        notifications.add(notification1);
        notifications.add(notification2);

        DueDateStrategy dueDateStrategy = new DueDateStrategy();
        dueDateStrategy.checkDate(todo, notifications);

        verify(notification1, times(1)).notify(any(ReachedDueDateEvent.class));
        verify(notification2, times(1)).notify(any(ReachedDueDateEvent.class));
    }


    @Test
    public void testCheckDate_NoNotificationSent()
    {
        Todo todo = TodoFactory.createTodo(UUID.randomUUID(), "Todo", "Todo Description", LocalDate.now().plusDays(2), LocalDate.now());
        List<Notification> notifications = new ArrayList<>();
        Notification notification = mock(Notification.class);
        notifications.add(notification);

        DueDateStrategy dueDateStrategy = new DueDateStrategy();
        dueDateStrategy.checkDate(todo, notifications);

        verify(notification, times(0)).notify(null);
    }
}
