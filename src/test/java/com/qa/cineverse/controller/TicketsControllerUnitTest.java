package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Tickets;
import com.qa.cineverse.dto.TicketsDTO;
import com.qa.cineverse.service.TicketsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TicketsControllerUnitTest {

    @InjectMocks
    private TicketsController ticketsController;

    @Mock
    private TicketsService service;

    private List<Tickets> tickets;

    private Tickets testTickets;

    private Tickets testTicketsWithId;

    private final long id = 1L;

    private TicketsDTO ticketsDTO;

    private final ModelMapper mapper = new ModelMapper();

    private TicketsDTO mapToDTO(Tickets tickets) { return this.mapper.map(tickets, TicketsDTO.class);}

    @Before
    public void setUp(){
        this.tickets = new ArrayList<> ();
        this.testTickets = new Tickets ("A1", "deluxe", 1L);
        this.tickets.add(testTickets);
        this.testTicketsWithId = new Tickets (testTickets.getTicketType(), testTickets.getSeatNo(), testTickets.getScreenId());
        this.testTicketsWithId.setTicketsId (this.id);
        this.ticketsDTO = this.mapToDTO(testTicketsWithId);
    }

    @Test
    public void getAllTicketsTest(){
        when(service.readTickets()).thenReturn(this.tickets.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No tickets found", this.ticketsController.getAllTickets().getBody().isEmpty());
        verify(service, times(1)).readTickets ();
    }

    @Test
    public void deleteTicketsTestFalse(){
        this.ticketsController.deleteTicket(id);
        verify(service, times(1)).deleteTicket(id);
    }
}
