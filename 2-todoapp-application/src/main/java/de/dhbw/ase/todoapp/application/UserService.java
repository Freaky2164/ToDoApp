package de.dhbw.ase.todoapp.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.entities.user.UserRepository;
import de.dhbw.ase.todoapp.domain.vo.Email;


@Service
public class UserService
{
    private UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    public User registerUser(final User user)
    {
        return userRepository.createUser(user);
    }


    public User findUserByMail(final Email mail)
    {
        return userRepository.findUserByMail(mail);
    }
}
