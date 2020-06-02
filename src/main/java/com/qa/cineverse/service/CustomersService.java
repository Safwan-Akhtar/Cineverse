package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Tickets;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.exception.CustomersNotFoundException;
import com.qa.cineverse.repo.CustomersRepo;
import com.qa.cineverse.repo.TicketsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomersService {

    private final CustomersRepo customersRepo;

    private final TicketsRepo ticketsRepo;

    private final ModelMapper mapper;

    @Autowired
    public CustomersService(CustomersRepo customersRepo, TicketsRepo ticketsRepo, ModelMapper mapper) {
        this.customersRepo = customersRepo;
        this.ticketsRepo = ticketsRepo;
        this.mapper = mapper;
    }

    private CustomersDTO mapToDTO(Customers customers) {
        return this.mapper.map(customers, CustomersDTO.class);
    }

    public List<CustomersDTO> readCustomers(){
        return this.customersRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CustomersDTO createCustomer(Customers customers){
        return this.mapToDTO(this.customersRepo.save(customers));
    }

    public CustomersDTO findCustomersById(Long id){
        return this.mapToDTO(this.customersRepo.findById(id).orElseThrow(CustomersNotFoundException::new));
    }

    public boolean deleteCustomers(Long id){
        if(!this.customersRepo.existsById(id)){
            throw new CustomersNotFoundException ();
        }
        this.customersRepo.deleteById(id);
        return this.customersRepo.existsById(id);
    }

    public CustomersDTO addTicketsToCustomer(Long id, Tickets tickets){
        Customers customers = this.customersRepo.findById(id).orElseThrow(CustomersNotFoundException::new);
        Tickets tmp = this.ticketsRepo.saveAndFlush(tickets);
        customers.getTickets ().add(tmp);
        return this.mapToDTO(this.customersRepo.saveAndFlush(customers));
    }
}
