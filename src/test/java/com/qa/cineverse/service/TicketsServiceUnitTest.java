package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Tickets;
import com.qa.cineverse.dto.CustomersDTOTest;
import com.qa.cineverse.dto.TicketsDTO;
import com.qa.cineverse.repo.TicketsRepo;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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




}
