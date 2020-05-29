package com.qa.cineverse.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TicketsTest {

    private Tickets tickets;
    private Tickets other;

    @Before
    public void setUp(){
        tickets = new Tickets(1L, "A1", "adult");
        other = new Tickets("B2", "child");
    }

    @Test
    public void settersTest() {
        assertNotNull(tickets.getTicketsId());
        assertNotNull(tickets.getSeatNo());
        assertNotNull(tickets.getTicketType());


    }

}
