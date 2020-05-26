package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.service.CustomersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerUnitTest {

    @InjectMocks
    private CustomersController characterController;

    @Mock
    private CustomersService service;

    private List<Customers> customers;

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
        this.customers = new ArrayList<> ();
        this.testCustomers = new Customers ("Luke");
        this.customers.add(testCustomers);
        this.testCustomersWithId = new Customers (testCustomers.getName());
        this.testCustomersWithId.setCustomerId (this.id);
        this.customersDTO = this.mapToDTO(testCustomersWithId);
    }

    @Test
    public void getAllCharacterTest(){
        when(service.readCustomers ()).thenReturn(this.customers.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No character found", this.characterController.getAllCustomers().getBody().isEmpty());
        verify(service, times(1)).readCustomers ();
    }




}
