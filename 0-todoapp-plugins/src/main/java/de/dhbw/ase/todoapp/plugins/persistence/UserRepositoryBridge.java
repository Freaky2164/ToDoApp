package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.user.Email;
import de.dhbw.ase.todoapp.domain.user.User;
import de.dhbw.ase.todoapp.domain.user.UserRepository;


@Repository
public class UserRepositoryBridge implements UserRepository
{
    @Autowired
    SpringDataUserRepository springDataUserRepository;

    @Override
    public User findById(UUID userId)
    {
        Optional<User> optionalUser = springDataUserRepository.findById(userId);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }


    @Override
    public User findByMail(Email mail)
    {
        return springDataUserRepository.findUserByMail(mail);
    }


    @Override
    public List<User> findAll()
    {
        return springDataUserRepository.findAll();
    }


    @Override
    public User createUser(User user)
    {
        return springDataUserRepository.save(user);
    }
}
