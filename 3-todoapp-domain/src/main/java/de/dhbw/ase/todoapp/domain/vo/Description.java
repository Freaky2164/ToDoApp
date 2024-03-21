package de.dhbw.ase.todoapp.domain.vo;


import java.util.Objects;

import org.apache.commons.lang3.Validate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public final class Description
{
    @Column
    private final String descriptionText;

    protected Description()
    {
        // default constructor for JPA
        this.descriptionText = "";
    }


    public Description(final String descriptionText)
    {
        Validate.notBlank(descriptionText);
        this.descriptionText = descriptionText;
    }


    public String getDescription()
    {
        return descriptionText;
    }


    public Description changeDescription(final String descriptionText)
    {
        return new Description(descriptionText);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(descriptionText);
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Description other = (Description)obj;
        return Objects.equals(descriptionText, other.descriptionText);
    }


    @Override
    public String toString()
    {
        return descriptionText;
    }
}
