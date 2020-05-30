package com.qa.cineverse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Tickets;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.TicketsDTO;
import com.qa.cineverse.repo.TicketsRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TicketsControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private TicketsRepo repository;

    @Autowired
    private ModelMapper mapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Tickets testTickets;

    private Tickets testTicketsWithId;

    private long id;

    private TicketsDTO ticketsDTO;

    private TicketsDTO mapToDTO(Tickets tickets){
        return this.mapper.map(tickets, TicketsDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testTickets = new Tickets ("A1", "deluxe");
        this.testTicketsWithId = this.repository.save(testTickets);
        this.id = testTicketsWithId.getTicketsId ();
        this.ticketsDTO = this.mapToDTO(testTicketsWithId);
    }

    @Test
    public void getAllTicketsTest() throws Exception {
        List<TicketsDTO> ticketsDTOList = new ArrayList<> ();
        ticketsDTOList.add(ticketsDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllTickets")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(ticketsDTOList));
    }
}
