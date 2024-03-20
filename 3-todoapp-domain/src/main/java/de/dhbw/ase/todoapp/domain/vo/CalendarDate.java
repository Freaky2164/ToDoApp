package de.dhbw.ase.todoapp.domain.vo;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;

import de.dhbw.ase.todoapp.domain.exceptions.PastDateException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public final class CalendarDate
{
    @Column
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
        LocalDate currentDate = LocalDate.now();
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd, MMMM, yyyy");
        return sdf.format(date);
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
}
