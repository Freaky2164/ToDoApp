package de.dhbw.ase.todoapp.domain.entities.user;


import de.dhbw.ase.todoapp.domain.vo.Email;
import de.dhbw.ase.todoapp.domain.vo.Password;


public class UserFactory
{
    public static User createUser(String mailAdress, String passwordValue)
    {
        Email mail = new Email(mailAdress);
        Password password = new Password(passwordValue);
        return new User(mail, password);
    }
}
