package de.dhbw.ase.todoapp.domain.user;


import java.util.List;
import java.util.UUID;


public interface UserRepository
{
    public User findById(final UUID userId);


    public User findByMail(final Email mail);


    public List<User> findAll();


    public User createUser(final User user);
}
