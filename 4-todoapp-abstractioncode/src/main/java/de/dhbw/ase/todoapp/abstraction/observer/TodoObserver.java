package de.dhbw.ase.todoapp.abstraction.observer;

import de.dhbw.ase.todoapp.abstraction.event.TodoEvent;

public interface TodoObserver
{
    void notify(TodoEvent event);
}
