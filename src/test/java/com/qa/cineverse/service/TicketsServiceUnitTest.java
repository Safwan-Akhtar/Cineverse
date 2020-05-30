package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Tickets;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.CustomersDTOTest;
import com.qa.cineverse.dto.TicketsDTO;
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
        this.testTickets = new Tickets ("A1", "deluxe");
        this.ticketsList.add(testTickets);
        this.testTicketsWithID = new Tickets (testTickets.getTicketType(), testTickets.getSeatNo());
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
}
