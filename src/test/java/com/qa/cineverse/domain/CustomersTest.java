package com.qa.cineverse.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomersTest {

    private Customers customers;
    private Customers other;

    @Before
    public void setUp() {
        customers = new Customers(1L, "Luke", 1L);
        other = new Customers("Felix", 1L);
    }

    @Test
    public void settersTest() {
        assertNotNull(customers.getCustomersId());
        assertNotNull(customers.getName());
        assertNotNull(customers.getSeatNo());

        customers.setCustomersId(null);
        assertNull(customers.getCustomersId());
        customers.setName(null);
        assertNull(customers.getName());
        customers.setSeatNo(null);
        assertNull(customers.getSeatNo());
    }

    @Test
    public void equalsWithNull() { assertFalse(customers.equals(null)); }

    @Test
    public void equalsWithDifferentObject() { assertFalse(customers.equals (new Object())); }

    @Test
    public void createCustomersWithId() {
        assertEquals(1L, customers.getCustomersId(), 0);
        assertEquals("Luke", customers.getName());
        assertEquals(1L, customers.getSeatNo(), 0);
    }

    @Test
    public void checkEquality() {
        assertTrue(customers.equals(customers));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertFalse(customers.equals(other));
    }

    @Test(expected = NullPointerException.class)
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

    @Test(expected = NullPointerException.class)
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
        Customers characterSheet = new Customers("Felix", 1L);
        assertNull(characterSheet.getCustomersId ());
        assertNotNull(characterSheet.getName());
    }

    @Test
    public void hashCodeTestWithNull() {
        Customers customers = new Customers(null, null);
        Customers other = new Customers(null, null, null);
        assertEquals(customers.hashCode(), other.hashCode());
    }

}
