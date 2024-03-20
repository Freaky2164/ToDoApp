package de.dhbw.ase.todoapp.domain.entities.user;


import de.dhbw.ase.todoapp.domain.vo.Email;


public interface UserRepository
{
    public User findUserByMail(final Email mail);


    public User createUser(final User user);
}
