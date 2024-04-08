package de.dhbw.ase.todoapp.plugins.controller;


import org.springframework.beans.factory.annotation.Autowired;

import de.dhbw.ase.todoapp.abstraction.observer.Observable;
import de.dhbw.ase.todoapp.application.NotificationService;
import de.dhbw.ase.todoapp.application.TodoService;
import de.dhbw.ase.todoapp.application.UserService;


public abstract class BaseController extends Observable
{
    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;
}
