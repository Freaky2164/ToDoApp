package de.dhbw.ase.todoapp.plugins.persistence;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.ase.todoapp.domain.user.Email;
import de.dhbw.ase.todoapp.domain.user.User;


@Repository
public interface SpringDataUserRepository extends JpaRepository<User, UUID>
{
    User findUserByMail(final Email mail);
}
