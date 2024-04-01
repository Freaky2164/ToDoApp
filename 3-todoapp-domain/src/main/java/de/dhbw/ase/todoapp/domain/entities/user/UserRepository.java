package de.dhbw.ase.todoapp.domain.entities.user;


import java.util.List;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.vo.Email;


public interface UserRepository
{
    public User findById(final UUID userId);


    public User findByMail(final Email mail);


    public List<User> findAll();


    public User createUser(final User user);
}
