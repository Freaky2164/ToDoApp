package de.dhbw.ase.todoapp.application;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.entities.user.UserFactory;
import de.dhbw.ase.todoapp.domain.entities.user.UserRepository;
import de.dhbw.ase.todoapp.domain.exceptions.InvalidLoginException;
import de.dhbw.ase.todoapp.domain.vo.Email;
import de.dhbw.ase.todoapp.domain.vo.Password;


@Service
public class UserService
{
    private UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    public User findUserById(final UUID userId)
    {
        return userRepository.findById(userId);
    }


    public User findUserByMail(final String mailAdress)
    {
        return userRepository.findByMail(new Email(mailAdress));
    }


    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }


    public User registerUser(final String mailAdress, final String password)
    {
        if (mailAdress.isEmpty() || password.isEmpty())
        {
            throw new InvalidLoginException("Mail adress or password cannot be empty.");
        }
        if (userRepository.findByMail(new Email(mailAdress)) != null)
        {
            throw new InvalidLoginException("There already exists a user with the provided mail adress.");
        }
        User newUser = UserFactory.createUser(mailAdress, password);
        return userRepository.createUser(newUser);
    }


    public UUID authenticateUser(final String mailAdress, final String password)
    {
        User user = userRepository.findByMail(new Email(mailAdress));
        if (user != null)
        {
            Password storedPassword = new Password(password, user.getPassword().getSalt());
            return storedPassword.equals(user.getPassword()) ? user.getId() : null;
        }
        return null;
    }
}
