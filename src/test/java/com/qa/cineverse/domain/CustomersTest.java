package com.qa.cineverse.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomersTest {

    private Customers customers;
    private Customers other;

    @Before
    public void setUp() {
        customers = new Customers(1L, "Luke");
        other = new Customers("Felix");
    }

    @Test
    public void settersTest() {
        assertNotNull(customers.getCustomersId());
        assertNotNull(customers.getName());

        customers.setCustomersId(null);
        assertNull(customers.getCustomersId());
        customers.setName(null);
        assertNull(customers.getName());
    }

    @Test
    public void equalsWithNull() { assertFalse(customers.equals(null)); }




}
