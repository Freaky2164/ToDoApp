package de.dhbw.ase.todoapp.domain.user;

public class UserFactory
{
    public static User createUser(String mailAdress, String passwordValue)
    {
        Email mail = new Email(mailAdress);
        Password password = new Password(passwordValue);
        return new User(mail, password);
    }
}
