package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Tickets;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.TicketsDTO;
import com.qa.cineverse.repo.CustomersRepo;
import com.qa.cineverse.repo.TicketsRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketsServiceIntegrationTest {

    @Autowired
    private TicketsService service;

    @Autowired
    private TicketsRepo repository;

    @Autowired
    private ModelMapper mapper;

    private Tickets testTickets;

    private Tickets testTicketsWithId;

    private TicketsDTO mapToDTO(Tickets tickets){
        return this.mapper.map(tickets, TicketsDTO.class);
    }

    @Before
    public void setUp(){
        this.testTickets = new Tickets ("A1", "deluxe", 1L, 1L);
        this.repository.deleteAll();
        this.testTicketsWithId = this.repository.save(this.testTickets);
    }

    @Test
    public void readTicketsTest(){
        assertThat(this.service.readTickets())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testTicketsWithId)).collect(Collectors.toList())
                );
    }

    @Test
    public void deleteTicketsTest(){
        assertThat(this.service.deleteTicket(this.testTicketsWithId.getTicketsId ())).isFalse();
    }
}
