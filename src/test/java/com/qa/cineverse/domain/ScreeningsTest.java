package com.qa.cineverse.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ScreeningsTest {

    private Screenings screenings;
    private Screenings other;
    private LocalDateTime date1;
    private LocalDateTime date2;

    @Before
    public void setUp() {
        date1 = LocalDateTime.of(LocalDate.ofEpochDay(2007-12-3), LocalTime.MIN);
        date2 = LocalDateTime.of(LocalDate.ofEpochDay(2107-11-13), LocalTime.MAX);
        screenings = new Screenings(1L, date1, "Deluxe");
        other = new Screenings(date2, "Standard");
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
        screenings.setScreenType(null);
        assertNull(screenings.getScreenType());
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
    public void createScreeningsWithId() {
        assertEquals(1L, screenings.getScreeningsId(), 0);
        assertEquals(date1, screenings.getMovieDateTime());
        assertEquals("Deluxe", screenings.getScreenType());
    }

    @Test
    public void checkEquality() {
        assertTrue(screenings.equals(screenings));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertFalse(screenings.equals(other));
    }

    @Test(expected = NullPointerException.class)
    public void screeningsDateNullButOtherDateNotNull() {
        screenings.setMovieDateTime(null);
        other.setScreeningsId (1L);
        assertFalse(screenings.equals(other));
    }

    @Test
    public void screeningsDateNotEqual() {
        other.setMovieDateTime(date2);
        assertFalse(screenings.equals(other));
    }

    @Test
    public void screeningsTypeNullButOtherTypeNotNull() {
        screenings.setScreenType(null);
        assertFalse(screenings.equals(other));
    }

    @Test
    public void screeningsTypeNotEqual() {
        other.setScreenType("Special");
        assertFalse(screenings.equals(other));
    }

    @Test(expected = NullPointerException.class)
    public void nullId() {
        screenings.setScreeningsId(null);
        assertFalse(screenings.equals(other));
    }

    @Test
    public void screeningsIDDifferent() {
        other.setScreeningsId(1L);
        assertFalse(screenings.equals(other));
    }

    @Test
    public void otherIdDifferent() {
        other.setScreeningsId(2L);
        assertFalse(screenings.equals(other));
    }

    @Test
    public void constructorWithoutId() {
        Screenings screenings = new Screenings (date1, "Deluxe");
        assertNull(screenings.getScreeningsId());
        assertNotNull(screenings.getMovieDateTime());
        assertNotNull(screenings.getScreenType());
    }

    @Test
    public void hashCodeTestWithNull() {
        Screenings screenings = new Screenings(null, null);
        Screenings other = new Screenings(null, null, null);
        assertEquals(screenings.hashCode(), other.hashCode());
    }

}
