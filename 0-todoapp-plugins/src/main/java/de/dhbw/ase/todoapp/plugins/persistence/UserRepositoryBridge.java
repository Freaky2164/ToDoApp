package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.entities.user.UserRepository;
import de.dhbw.ase.todoapp.domain.vo.Email;


@Repository
public class UserRepositoryBridge implements UserRepository
{

    @Autowired
    SpringDataUserRepository springDataUserRepository;

    @Override
    public User findUserById(UUID userId)
    {
        Optional<User> optionalUser = springDataUserRepository.findById(userId);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }


    @Override
    public User findUserByMail(Email mail)
    {
        return springDataUserRepository.findUserByMail(mail);
    }


    @Override
    public User createUser(User user)
    {
        return springDataUserRepository.save(user);
    }
}
