package de.dhbw.ase.todoapp.domain.todo;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import de.dhbw.ase.todoapp.domain.todo.CalendarDate;


public class CalendarDateTest
{
    @Test
    public void testValidDate()
    {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        CalendarDate calendarDate = new CalendarDate(futureDate);
        assertEquals(futureDate, calendarDate.getDate());
    }


    @Test(expected = NullPointerException.class)
    public void testNullDate()
    {
        new CalendarDate(null);
    }


    @Test
    public void testChangeDate()
    {
        LocalDate initialDate = LocalDate.now().plusDays(1);
        CalendarDate calendarDate = new CalendarDate(initialDate);

        LocalDate newDate = LocalDate.now().plusDays(8);
        CalendarDate changedDate = calendarDate.changeDate(newDate);

        assertEquals(newDate, changedDate.getDate());
    }


    @Test
    public void testFormatDate()
    {
        LocalDate currentDate = LocalDate.now();
        CalendarDate calendarDate = new CalendarDate(currentDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = currentDate.format(formatter);

        assertEquals(formattedDate, calendarDate.formatDate());
    }


    @Test
    public void testFutureDate()
    {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        CalendarDate futureCalendarDate = new CalendarDate(futureDate);
        assertFalse(futureCalendarDate.isPastDate());
    }


    @Test
    public void testEquals()
    {
        LocalDate futureDate1 = LocalDate.now().plusDays(1);
        CalendarDate futureCalendarDate1 = new CalendarDate(futureDate1);
        CalendarDate futureCalendarDate2 = new CalendarDate(futureDate1);

        LocalDate futureDate2 = LocalDate.now().plusDays(2);
        CalendarDate futureCalendarDate3 = new CalendarDate(futureDate2);

        assertTrue(futureCalendarDate1.equals(futureCalendarDate2));
        assertFalse(futureCalendarDate1.equals(futureCalendarDate3));
    }
}
