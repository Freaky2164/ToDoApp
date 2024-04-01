package de.dhbw.ase.todoapp.plugins.persistence;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.entities.user.UserFactory;
import de.dhbw.ase.todoapp.domain.vo.Email;


public class UserRepositoryBridgeTest
{
    private SpringDataUserRepository springDataUserRepositoryMock;
    private UserRepositoryBridge userRepositoryBridge;

    @Before
    public void setUp()
    {
        springDataUserRepositoryMock = mock(SpringDataUserRepository.class);
        userRepositoryBridge = new UserRepositoryBridge();
        userRepositoryBridge.springDataUserRepository = springDataUserRepositoryMock;
    }


    @Test
    public void testFindUserById()
    {
        UUID userId = UUID.randomUUID();
        User expectedUser = UserFactory.createUser("test1@example.com", "Password123");

        when(springDataUserRepositoryMock.findById(userId)).thenReturn(Optional.of(expectedUser));

        User actualUser = userRepositoryBridge.findById(userId);

        assertEquals(expectedUser, actualUser);
    }


    @Test
    public void testFindUserByMail()
    {
        Email email = new Email("test@example.com");
        User expectedUser = UserFactory.createUser("test1@example.com", "Password123");

        when(springDataUserRepositoryMock.findUserByMail(email)).thenReturn(expectedUser);

        User actualUser = userRepositoryBridge.findByMail(email);

        assertEquals(expectedUser, actualUser);
    }


    @Test
    public void testFindAllUsers()
    {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(UserFactory.createUser("test1@example.com", "Password123"));
        expectedUsers.add(UserFactory.createUser("test1@example.com", "Password123"));

        when(springDataUserRepositoryMock.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userRepositoryBridge.findAll();

        assertEquals(expectedUsers, actualUsers);
    }


    @Test
    public void testCreateUser()
    {
        User user = UserFactory.createUser("test1@example.com", "Password123");

        when(springDataUserRepositoryMock.save(user)).thenReturn(user);

        User createdUser = userRepositoryBridge.createUser(user);

        assertEquals(user, createdUser);
    }
}
