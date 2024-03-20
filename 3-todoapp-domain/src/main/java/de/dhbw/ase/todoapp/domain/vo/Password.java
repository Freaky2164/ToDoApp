package de.dhbw.ase.todoapp.domain.vo;


import java.util.Objects;

import org.apache.commons.lang3.Validate;

import de.dhbw.ase.todoapp.domain.exceptions.InvalidPasswordException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public final class Password
{
    @Column
    private final String passwordValue;

    protected Password()
    {
        // default constructor for JPA
        this.passwordValue = "";
    }


    public Password(final String passwordValue)
    {
        Validate.notBlank(passwordValue);
        if (!isValid(passwordValue))
        {
            throw new InvalidPasswordException();
        }
        this.passwordValue = passwordValue;
    }


    private boolean isValid(final String passwordValue)
    {
        return passwordValue.length() >= 8;
    }


    public String getPassword()
    {
        return passwordValue;
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(passwordValue);
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Password other = (Password)obj;
        return Objects.equals(passwordValue, other.passwordValue);
    }
}
