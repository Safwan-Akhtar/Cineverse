package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.repo.CustomersRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CustomerService {

    private final CustomersRepo customersRepo;

    private final ModelMapper mapper;

    @Autowired
    public CustomerService(CustomersRepo customersRepo, ModelMapper mapper) {
        this.customersRepo = customersRepo;
        this.mapper = mapper;
    }

    private CustomersDTO mapToDTO(Customers customers) {
        return this.mapper.map(customers, CustomersDTO.class);
    }




}
