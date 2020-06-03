package com.qa.cineverse.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TicketsTest {

    private Tickets tickets;
    private Tickets other;

    private List<Customers> customers = new ArrayList<> ();
    private List<Customers> otherCustomers = new ArrayList<> ();

    @Before
    public void setUp() {
        tickets = new Tickets("A1", "deluxe", 1L, 1L);
        other = new Tickets("B2", "standard", 3L, 2L);

        customers.add(new Customers ("Jim"));
        otherCustomers.add(new Customers ("Jam"));
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

    @Test(expected=NullPointerException.class)
    public void ticketsScreenNullButOtherScreenNotNull() {
        tickets.setScreenId(null);
        other.setTicketsId (1L);
        assertFalse(tickets.equals(other));
    }

    @Test
    public void ticketsScreenIdNotEqual() {
        other.setScreenId(1L);
        assertFalse(tickets.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void ticketsUserNullButOtherUserNotNull() {
        tickets.setUserId(null);
        other.setTicketsId (1L);
        assertFalse(tickets.equals(other));
    }

    @Test
    public void ticketsUserIdNotEqual() {
        other.setUserId(1L);
        assertFalse(tickets.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void ticketCustomersNullButOtherTicketsNotNull() {
        customers.add(new Customers (null));
        tickets.setCustomers(null);
        other.setTicketsId (1L);
        assertFalse(tickets.equals(other));
    }

    @Test
    public void customersTicketsNotEqual() {
        otherCustomers.add(new Customers ("Jam"));
        otherCustomers.add(new Customers ("Pam"));
        other.setCustomers(otherCustomers);
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
        Tickets tickets = new Tickets("A1", "deluxe", 1L, 1L);
        assertNull(tickets.getTicketsId ());
        assertNotNull(tickets.getTicketType());
        assertNotNull(tickets.getSeatNo());
        assertNotNull(tickets.getScreenId());
    }

    @Test(expected=NullPointerException.class)
    public void hashCodeTestWithNull() {
        Tickets tickets = new Tickets(null, null, null, null);
        Tickets other = new Tickets(null, null, null, null);
        assertEquals(tickets.hashCode(), other.hashCode());
    }
}
