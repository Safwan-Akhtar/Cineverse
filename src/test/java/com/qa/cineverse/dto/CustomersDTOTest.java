package com.qa.cineverse.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class CustomersDTOTest {

    private CustomersDTO customersDTO;
    private CustomersDTO other;


    @Before
    public void setUp() {
        customersDTO = new CustomersDTO ("Felix", "Longbi");
        other = new CustomersDTO ("Saf", "whatwhat");
    }

    @Test
    public void equalsWithNull() {
        assertFalse(customersDTO.equals(null));
    }

    @Test
    public void equalsWithDifferentObject() {
        assertFalse(customersDTO.equals(new Object()));
    }

    @Test
    public void checkEquality() {
        assertTrue(customersDTO.equals(customersDTO));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertFalse(customersDTO.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void customersDTONameNullButOtherNameNotNull() {
        customersDTO.setName(null);
        assertFalse(customersDTO.equals(other));
    }

    @Test
    public void customersDTONamesNotEqual() {
        other.setName("Saf");
        assertFalse(customersDTO.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void ticketsUserNullButOtherUserNotNull() {
        customersDTO.setUsername(null);
        other.setCustomersId (1L);
        assertFalse(customersDTO.equals(other));
    }

    @Test
    public void ticketsUserIdNotEqual() {
        other.setUsername("whatwhat");
        assertFalse(customersDTO.equals(other));
    }

    @Test
    public void nullId() {
        customersDTO.setCustomersId(null);
        assertFalse(customersDTO.equals(other));
    }

    @Test
    public void customersDTOIDDifferent() {
        other.setCustomersId(1L);
        assertFalse(customersDTO.equals(other));
    }

    @Test
    public void otherIdDifferent() {
        other.setCustomersId(2L);
        assertFalse(customersDTO.equals(other));
    }

    @Test
    public void constructorWithoutId() {
        CustomersDTO customersDTO = new CustomersDTO("Felix", "longbi");
        assertNull(customersDTO.getCustomersId ());
        assertNotNull(customersDTO.getName());
    }

    @Test(expected=NullPointerException.class)
    public void hashCodeTestWithNull() {
        CustomersDTO customersDTO = new CustomersDTO(null, null);
        CustomersDTO other = new CustomersDTO(null, null);
        assertEquals(customersDTO.hashCode(), other.hashCode());
    }
}