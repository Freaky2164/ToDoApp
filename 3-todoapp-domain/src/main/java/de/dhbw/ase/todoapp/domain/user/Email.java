package de.dhbw.ase.todoapp.domain.user;


import java.util.Objects;
import java.util.regex.Pattern;

import de.dhbw.ase.todoapp.domain.exceptions.InvalidEmailException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public final class Email
{
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");

    @Column(name = "mailAdress", nullable = false)
    private final String mailAdress;

    protected Email()
    {
        // default constructor for JPA
        this.mailAdress = "";
    }


    public Email(final String mailAdress)
    {
        Objects.requireNonNull(mailAdress);
        if (!isValid(mailAdress))
        {
            throw new InvalidEmailException();
        }
        this.mailAdress = mailAdress;
    }


    private boolean isValid(final String mailAdress)
    {
        return EMAIL_PATTERN.matcher(mailAdress).matches();
    }


    public String getMailAdress()
    {
        return mailAdress;
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(mailAdress);
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Email other = (Email)obj;
        return Objects.equals(mailAdress, other.mailAdress);
    }
}
