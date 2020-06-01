package com.qa.cineverse.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicketsDTOTest {

    private TicketsDTO ticketsDTO;
    private TicketsDTO other;

    @Before
    public void setUp() {
        ticketsDTO = new TicketsDTO ("A1", "deluxe");
        other = new TicketsDTO ("B2", "standard");
    }

    @Test
    public void equalsWithNull() {
        assertFalse(ticketsDTO.equals(null));
    }

    @Test
    public void equalsWithDifferentObject() {
        assertFalse(ticketsDTO.equals(new Object()));
    }

    @Test
    public void checkEquality() {
        assertTrue(ticketsDTO.equals(ticketsDTO));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertFalse(ticketsDTO.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void ticketsDTOTypeNullButOtherTypeNotNull() {
        ticketsDTO.setTicketType(null);
        assertFalse(ticketsDTO.equals(other));
    }

    @Test
    public void customersDTONamesNotEqual() {
        other.setTicketType("standard");
        assertFalse(ticketsDTO.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void ticketsDTOSeatNullButOtherSeatNotNull() {
        ticketsDTO.setTicketType(null);
        assertFalse(ticketsDTO.equals(other));
    }

    @Test
    public void ticketsDTOSeatNotEqual() {
        other.setTicketType("B2");
        assertFalse(ticketsDTO.equals(other));
    }

    @Test
    public void nullId() {
        ticketsDTO.setTicketsId(null);
        assertFalse(ticketsDTO.equals(other));
    }

    @Test
    public void ticketsDTOIDDifferent() {
        other.setTicketsId(1L);
        assertFalse(ticketsDTO.equals(other));
    }

    @Test
    public void otherIdDifferent() {
        other.setTicketsId(2L);
        assertFalse(ticketsDTO.equals(other));
    }

//    @Test
//    public void constructorWithoutId() {
//        TicketsDTO ticketsDTO = new TicketsDTO("A1", "deluxe");
//        assertNull(TicketsDTO.getTicketsId ());
//        assertNotNull(TicketsDTO.getTicketType());
//        assertNotNull(TicketsDTO.getSeatNo());
//    }

    @Test(expected=NullPointerException.class)
    public void hashCodeTestWithNull() {
        TicketsDTO ticketsDTO = new TicketsDTO(null, null);
        TicketsDTO other = new TicketsDTO(null, null);
        assertEquals(ticketsDTO.hashCode(), other.hashCode());
    }

}
