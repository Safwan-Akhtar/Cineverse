package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.service.CustomersService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerUnitTest {

    @InjectMocks
    private CustomersController characterController;

    @Mock
    private CustomersService service;

    private List<Customers> characterSheet;

    private Customers testCustomers;

    private Customers testCustomersWithId;

    private final long id = 1L;

    private CustomersDTO customersDTO;

    private final ModelMapper mapper = new ModelMapper();

    private CustomersDTO mapToDTO(Customers customers){
        return this.mapper.map(customers, CustomersDTO.class);
    }

    @Before
    public void setUp(){
        this.characterSheet = new ArrayList<> ();
        this.testCustomers = new Customers ("Luke");
        this.characterSheet.add(testCustomers);
        this.testCustomersWithId = new Customers (testCustomers.getName());
        this.testCustomersWithId.setCustomerId (this.id);
        this.customersDTO = this.mapToDTO(testCustomersWithId);
    }



}
