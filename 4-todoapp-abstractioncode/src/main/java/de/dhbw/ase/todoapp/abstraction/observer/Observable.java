package de.dhbw.ase.todoapp.abstraction.observer;


import java.util.ArrayList;
import java.util.List;


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


    public void notifyObservers(String message)
    {
        for (TodoObserver observer : observers)
        {
            observer.notify(message);
        }
    }
}
