package de.dhbw.ase.todoapp.application;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.ase.todoapp.domain.entities.user.User;
import de.dhbw.ase.todoapp.domain.entities.user.UserFactory;
import de.dhbw.ase.todoapp.domain.entities.user.UserRepository;
import de.dhbw.ase.todoapp.domain.exceptions.InvalidLoginException;
import de.dhbw.ase.todoapp.domain.vo.Password;


public class UserServiceTest
{

    private UserService userService;
    private UserRepository mockUserRepository;

    @Before
    public void setUp()
    {
        mockUserRepository = mock(UserRepository.class);
        userService = new UserService(mockUserRepository);
    }


    @Test
    public void testFindUserById()
    {
        User mockUser = UserFactory.createUser("test@example.com", "Password123");
        UUID mockUserId = mockUser.getId();
        when(mockUserRepository.findUserById(mockUserId)).thenReturn(mockUser);

        User foundUser = userService.findUserById(mockUserId);
        assertNotNull(foundUser);
        assertEquals(mockUserId, foundUser.getId());
        assertEquals("test@example.com", foundUser.getEmail().getMailAdress());
        assertEquals(new Password("Password123", foundUser.getPassword().getSalt()), foundUser.getPassword());
    }


    @Test
    public void testFindUserByMail()
    {
        String mailAddress = "test@example.com";
        User mockUser = UserFactory.createUser(mailAddress, "Password123");
        when(mockUserRepository.findUserByMail(any())).thenReturn(mockUser);

        User foundUser = userService.findUserByMail(mailAddress);
        assertNotNull(foundUser);
        assertEquals(mailAddress, foundUser.getEmail().getMailAdress());
        assertEquals(new Password("Password123", foundUser.getPassword().getSalt()), foundUser.getPassword());
    }


    @Test
    public void testRegisterUser()
    {
        User mockUser = UserFactory.createUser("test@example.com", "Password123");
        when(mockUserRepository.findUserByMail(any())).thenReturn(null);
        when(mockUserRepository.createUser(any())).thenReturn(mockUser);

        User registeredUser = userService.registerUser("test@example.com", "Password123");
        assertNotNull(registeredUser);
        assertNotNull(registeredUser.getId());
        assertEquals("test@example.com", registeredUser.getEmail().getMailAdress());
        assertEquals(new Password("Password123", registeredUser.getPassword().getSalt()), registeredUser.getPassword());
    }


    @Test(expected = InvalidLoginException.class)
    public void testRegisterUserWithEmptyMail()
    {
        userService.registerUser("", "Password123");
    }


    @Test(expected = InvalidLoginException.class)
    public void testRegisterUserWithEmptyPassword()
    {
        userService.registerUser("test@example.com", "");
    }


    @Test(expected = InvalidLoginException.class)
    public void testRegisterUserWithExistingMail()
    {
        when(mockUserRepository.findUserByMail(any())).thenReturn(UserFactory.createUser("test@example.com", "Password123"));
        userService.registerUser("test@example.com", "Password123");
    }


    @Test
    public void testAuthenticateUser()
    {
        String mailAddress = "test@example.com";
        String password = "Password123";
        User mockUser = UserFactory.createUser(mailAddress, password);
        when(mockUserRepository.findUserByMail(any())).thenReturn(mockUser);

        UUID authenticatedUserId = userService.authenticateUser(mailAddress, password);
        assertEquals(mockUser.getId(), authenticatedUserId);
    }


    @Test
    public void testAuthenticateUserWithWrongPassword()
    {
        String mailAddress = "test@example.com";
        String password = "Password123";
        when(mockUserRepository.findUserByMail(any())).thenReturn(UserFactory.createUser("test@example.com", "TestPassword"));

        UUID authenticatedUserId = userService.authenticateUser(mailAddress, password);
        assertNull(authenticatedUserId);
    }


    @Test
    public void testAuthenticateUserWithNonExistingUser()
    {
        String mailAddress = "test@example.com";
        String password = "Password123";
        when(mockUserRepository.findUserByMail(any())).thenReturn(null);

        UUID authenticatedUserId = userService.authenticateUser(mailAddress, password);
        assertNull(authenticatedUserId);
    }
}
