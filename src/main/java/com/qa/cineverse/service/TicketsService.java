package com.qa.cineverse.service;

import com.qa.cineverse.domain.Tickets;
import com.qa.cineverse.dto.TicketsDTO;
import com.qa.cineverse.exception.TicketsNotFoundException;
import com.qa.cineverse.repo.CustomersRepo;
import com.qa.cineverse.repo.ScreeningsRepo;
import com.qa.cineverse.repo.TicketsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketsService {

    private final TicketsRepo ticketsRepo;

    private final ScreeningsRepo screeningsRepo;

    private final CustomersRepo customersRepo;

    private final ModelMapper mapper;

    @Autowired
    public TicketsService(TicketsRepo ticketsRepo, ScreeningsRepo screeningsRepo, CustomersRepo customersRepo, ModelMapper mapper) {
        this.ticketsRepo = ticketsRepo;
        this.screeningsRepo = screeningsRepo;
        this.customersRepo = customersRepo;
        this.mapper = mapper;
    }

    private TicketsDTO mapToDTO(Tickets tickets) { return this.mapper.map(tickets, TicketsDTO.class); }

    public List<TicketsDTO> readTickets() {
        return this.ticketsRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public boolean deleteTicket(Long id){
        if(!this.ticketsRepo.existsById(id)){
            throw new TicketsNotFoundException ();
        }
        this.ticketsRepo.deleteById(id);
        return this.ticketsRepo.existsById(id);
    }
}
