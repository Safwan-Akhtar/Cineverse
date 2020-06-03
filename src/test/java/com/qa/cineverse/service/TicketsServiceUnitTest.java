package com.qa.cineverse.service;

import com.qa.cineverse.domain.Tickets;
import com.qa.cineverse.dto.TicketsDTO;
import com.qa.cineverse.exception.TicketsNotFoundException;
import com.qa.cineverse.repo.TicketsRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TicketsServiceUnitTest {

    @InjectMocks
    private TicketsService service;

    @Mock
    private TicketsRepo repository;

    @Mock
    private ModelMapper mapper;

    private List<Tickets> ticketsList;

    private Tickets testTickets;

    private final long id = 1L;

    private Tickets testTicketsWithID;

    private TicketsDTO ticketsDTO;

    private TicketsDTO mapToDTO(Tickets tickets){
        return this.mapper.map(tickets, TicketsDTO.class);
    }

    @Before
    public void setUp(){
        this.ticketsList = new ArrayList<> ();
        this.testTickets = new Tickets ("A1", "deluxe", 1L, 1L);
        this.ticketsList.add(testTickets);
        this.testTicketsWithID = new Tickets (testTickets.getTicketType(), testTickets.getSeatNo(), testTickets.getScreenId(), testTickets.getUserId());
        this.testTicketsWithID.setTicketsId (id);
        this.ticketsDTO = this.mapToDTO(testTicketsWithID);
    }

    @Test
    public void getAllTicketsTest(){
        when(repository.findAll()).thenReturn(this.ticketsList);
        when(this.mapper.map(testTicketsWithID, TicketsDTO.class)).thenReturn(ticketsDTO);
        assertFalse("Service returned no Tickets", this.service.readTickets().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void deleteTicketsByExistingId(){
        when(this.repository.existsById(id)).thenReturn(true, false);
        assertFalse(service.deleteTicket(id));
        verify(repository, times(1)).deleteById(id);
        verify(repository, times(2)).existsById(id);
    }

    @Test(expected = TicketsNotFoundException.class)
    public void deleteTicketsByNonExistingId(){
        when(this.repository.existsById(id)).thenReturn(false);
        service.deleteTicket(id);
        verify(repository, times(1)).existsById(id);
    }
}
