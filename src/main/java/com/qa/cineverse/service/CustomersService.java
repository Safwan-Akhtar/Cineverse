package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.exception.CustomersNotFoundException;
import com.qa.cineverse.repo.CustomersRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomersService {

    private final CustomersRepo customersRepo;

    private final ModelMapper mapper;

    @Autowired
    public CustomersService(CustomersRepo customersRepo, ModelMapper mapper) {
        this.customersRepo = customersRepo;
        this.mapper = mapper;
    }

    private CustomersDTO mapToDTO(Customers customers) {
        return this.mapper.map(customers, CustomersDTO.class);
    }

    public List<CustomersDTO> readCustomers(){
        return this.customersRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CustomersDTO createCustomers(Customers customers){
        return this.mapToDTO(this.customersRepo.save(customers));
    }

    public CustomersDTO findCustomersById(Long id){
        return this.mapToDTO(this.customersRepo.findById(id).orElseThrow(CustomersNotFoundException::new));
    }

    public CustomersDTO updateCustomers(Long id, Customers customers){
        Customers update = this.customersRepo.findById(id).orElseThrow(CustomersNotFoundException::new);
        update.setName(customers.getName());
        Customers tempCustomers = this.customersRepo.save(update);
        return this.mapToDTO(tempCustomers);
    }

    public boolean deleteCustomers(Long id){
        if(!this.customersRepo.existsById(id)){
            throw new CustomersNotFoundException ();
        }
        this.customersRepo.deleteById(id);
        return this.customersRepo.existsById(id);
    }
}
