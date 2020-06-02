package com.qa.cineverse.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomersTest {

    private Customers customers;
    private Customers other;


    private List<Tickets> tickets = new ArrayList<> ();
    private List<Tickets> otherTickets = new ArrayList<> ();

    @Before
    public void setUp() {
        customers = new Customers("Luke");
        other = new Customers("Felix");

        tickets.add(new Tickets ("adult", "A1"));
        otherTickets.add(new Tickets ("child", "A3"));
    }

    @Test
    public void equalsWithNull() { assertFalse(customers.equals(null)); }

    @Test
    public void equalsWithDifferentObject() { assertFalse(customers.equals (new Object())); }

    @Test
    public void checkEquality() {
        assertTrue(customers.equals(customers));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertFalse(customers.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void customersNameNullButOtherNameNotNull() {
        customers.setName(null);
        other.setCustomersId (1L);
        assertFalse(customers.equals(other));
    }

    @Test
    public void customersNamesNotEqual() {
        other.setName("Saf");
        assertFalse(customers.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void customersTicketsNullButOtherTicketsNotNull() {
        tickets.add(new Tickets (null, null));
        customers.setTickets(null);
        other.setCustomersId (1L);
        assertFalse(customers.equals(other));
    }

    @Test
    public void customersTicketsNotEqual() {
        otherTickets.add(new Tickets ("child", "A3"));
        otherTickets.add(new Tickets ("student", "A4"));
        other.setTickets(otherTickets);
        assertFalse(customers.equals(other));
    }

    @Test
    public void nullId() {
        customers.setCustomersId(null);
        assertFalse(customers.equals(other));
    }

    @Test
    public void customersIDDifferent() {
        other.setCustomersId(1L);
        assertFalse(customers.equals(other));
    }

    @Test
    public void otherIdDifferent() {
        other.setCustomersId(2L);
        assertFalse(customers.equals(other));
    }

    @Test
    public void constructorWithoutId() {
        Customers customers = new Customers("Felix");
        assertNull(customers.getCustomersId ());
        assertNotNull(customers.getName());
    }

    @Test(expected=NullPointerException.class)
    public void hashCodeTestWithNull() {
        Customers customers = new Customers(null);
        Customers other = new Customers(null);
        assertEquals(customers.hashCode(), other.hashCode());
    }
}
