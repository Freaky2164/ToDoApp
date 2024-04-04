package de.dhbw.ase.todoapp.domain.vo;


import java.util.Objects;

import org.apache.commons.lang3.Validate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public final class Name
{
    @Column(name = "nameValue", nullable = false)
    private final String nameValue;

    protected Name()
    {
        // default constructor for JPA
        this.nameValue = "";
    }


    public Name(final String nameValue)
    {
        Validate.notBlank(nameValue);
        this.nameValue = nameValue;
    }


    public String getValue()
    {
        return nameValue;
    }


    public Name changeName(final String nameValue)
    {
        return new Name(nameValue);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(nameValue);
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Name other = (Name)obj;
        return Objects.equals(nameValue, other.nameValue);
    }


    @Override
    public String toString()
    {
        return nameValue;
    }
}
