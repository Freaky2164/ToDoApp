package de.dhbw.ase.todoapp.abstraction.observer;


import java.util.ArrayList;
import java.util.List;

import de.dhbw.ase.todoapp.abstraction.event.TodoEvent;


public class Observable
{
    List<TodoObserver> observers = new ArrayList<>();

    public void registerObserver(TodoObserver observer)
    {
        this.observers.add(observer);
    }


    public void unregisterObserver(TodoObserver observer)
    {
        this.observers.remove(observer);
    }


    public void notifyObservers(TodoEvent event)
    {
        for (TodoObserver observer : observers)
        {
            observer.notify(event);
        }
    }
}
