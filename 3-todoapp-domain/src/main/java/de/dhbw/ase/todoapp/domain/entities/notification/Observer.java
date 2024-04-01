package de.dhbw.ase.todoapp.domain.entities.notification;


public interface Observer
{
    void notify(String message);
}
