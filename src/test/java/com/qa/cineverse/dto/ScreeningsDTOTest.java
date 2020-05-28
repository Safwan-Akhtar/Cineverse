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
        screenings = new ScreeningsDTO (1L, date1, "Deluxe");
        other = new ScreeningsDTO(date2, "Standard");
    }

    @Test
    public void settersTest() {
        assertNotNull(screenings.getScreeningsId());
        assertNotNull(screenings.getMovieDateTime());
        assertNotNull(screenings.getScreenType());

        screenings.setScreeningsId(null);
        assertNull(screenings.getScreeningsId());
        screenings.setMovieDateTime(null);
        assertNull(screenings.getMovieDateTime());
        screenings.setScreenType (null);
        assertNull(screenings.getScreenType ());
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
    public void createScreeningsDTOWithId() {
        assertEquals(1L, screenings.getScreeningsId(), 0);
        assertEquals(date1, screenings.getMovieDateTime ());
        assertEquals("Deluxe", screenings.getScreenType ());
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
