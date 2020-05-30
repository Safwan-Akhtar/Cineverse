package com.qa.cineverse.dto;

import com.qa.cineverse.domain.Screenings;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ScreeningsDTOTest {

    private ScreeningsDTO screenings;
    private ScreeningsDTO other;
    private LocalDateTime date1;
    private LocalDateTime date2;

    @Before
    public void setUp() {
        date1 = LocalDateTime.of(LocalDate.ofEpochDay(2007-12-3), LocalTime.MIN);
        date2 = LocalDateTime.of(LocalDate.ofEpochDay(2107-11-13), LocalTime.MAX);
        screenings = new ScreeningsDTO (date1, 1L, "Deluxe",  "Guardians");
        other = new ScreeningsDTO(date2,  1L, "Standard","Thor");
    }

    @Test
    public void equalsWithNull() {
        assertFalse(screenings.equals(null));
    }

    @Test
    public void equalsWithDifferentObject() {
        assertFalse(screenings.equals(new Object()));
    }

    @Test
    public void checkEquality() {
        assertTrue(screenings.equals(screenings));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertFalse(screenings.equals(other));
    }


}
