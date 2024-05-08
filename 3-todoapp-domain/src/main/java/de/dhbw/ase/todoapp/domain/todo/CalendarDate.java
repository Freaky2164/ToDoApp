package de.dhbw.ase.todoapp.domain.todo;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import de.dhbw.ase.todoapp.domain.exceptions.PastDateException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public final class CalendarDate
{
    @Column(name = "date", nullable = false)
    private final LocalDate date;

    protected CalendarDate()
    {
        // default constructor for JPA
        this.date = null;
    }

    
    public CalendarDate(final LocalDate date)
    {
        Objects.requireNonNull(date);
        if (!this.isFutureDate(date))
        {
            throw new PastDateException();
        }
        this.date = date;
    }


    private boolean isFutureDate(final LocalDate date)
    {
        LocalDate currentDate = LocalDate.now().minusDays(1);
        return date.isAfter(currentDate);
    }


    public LocalDate getDate()
    {
        return this.date;
    }


    public CalendarDate changeDate(final LocalDate date)
    {
        return new CalendarDate(date);
    }


    public String formatDate()
    {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(pattern);
    }


    public boolean isPastDate()
    {
        LocalDate currentDate = LocalDate.now();
        return date.isBefore(currentDate);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(date);
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CalendarDate other = (CalendarDate)obj;
        return Objects.equals(date, other.date);
    }


    @Override
    public String toString()
    {
        return formatDate();
    }
}
