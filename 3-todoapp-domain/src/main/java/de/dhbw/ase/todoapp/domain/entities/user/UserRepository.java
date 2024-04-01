package de.dhbw.ase.todoapp.domain.entities.user;


import java.util.List;
import java.util.UUID;

import de.dhbw.ase.todoapp.domain.vo.Email;


public interface UserRepository
{
    public User findUserById(final UUID userId);


    public User findUserByMail(final Email mail);


    public List<User> findAllUsers();


    public User createUser(final User user);
}
