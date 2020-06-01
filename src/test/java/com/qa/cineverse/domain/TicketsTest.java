package com.qa.cineverse.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicketsTest {

    private Tickets tickets;
    private Tickets other;

    @Before
    public void setUp() {
        tickets = new Tickets("A1", "deluxe");
        other = new Tickets("B2", "standard");
    }

    @Test
    public void equalsWithNull() { assertFalse(tickets.equals(null)); }

    @Test
    public void equalsWithDifferentObject() { assertFalse(tickets.equals (new Object())); }

    @Test
    public void checkEquality() {
        assertTrue(tickets.equals(tickets));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertFalse(tickets.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void ticketsTypeNullButOtherTypeNotNull() {
        tickets.setTicketType(null);
        other.setTicketsId (1L);
        assertFalse(tickets.equals(other));
    }

    @Test
    public void ticketsTypeNotEqual() {
        other.setTicketType("standard");
        assertFalse(tickets.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void ticketsSeatNullButOtherSeatNotNull() {
        tickets.setSeatNo(null);
        other.setTicketsId (1L);
        assertFalse(tickets.equals(other));
    }

    @Test
    public void ticketsSeatNotEqual() {
        other.setSeatNo("C3");
        assertFalse(tickets.equals(other));
    }

    @Test
    public void customersIDDifferent() {
        other.setTicketsId(1L);
        assertFalse(tickets.equals(other));
    }

    @Test
    public void otherIdDifferent() {
        other.setTicketsId(2L);
        assertFalse(tickets.equals(other));
    }

    @Test
    public void constructorWithoutId() {
        Tickets tickets = new Tickets("A1", "deluxe");
        assertNull(tickets.getTicketsId ());
        assertNotNull(tickets.getTicketType());
        assertNotNull(tickets.getSeatNo());
    }

    @Test(expected=NullPointerException.class)
    public void hashCodeTestWithNull() {
        Tickets tickets = new Tickets(null, null);
        Tickets other = new Tickets(null, null);
        assertEquals(tickets.hashCode(), other.hashCode());
    }
}
