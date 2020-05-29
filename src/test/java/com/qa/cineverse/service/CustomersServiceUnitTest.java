package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTOTest;
import com.qa.cineverse.exception.CustomersNotFoundException;
import com.qa.cineverse.repo.CustomersRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CustomersServiceUnitTest {

    @InjectMocks
    private CustomersService service;

    @Mock
    private CustomersRepo repository;

    @Mock
    private ModelMapper mapper;

    private List<Customers> customersList;

    private Customers testCustomers;

    private final long id = 1L;

    private Customers testCustomersWithID;

    private CustomersDTOTest customersDTOTest;

    private CustomersDTOTest mapToDTO(Customers customers){
        return this.mapper.map(customers, CustomersDTOTest.class);
    }

    @Before
    public void setUp(){
        this.customersList = new ArrayList<> ();
        this.testCustomers = new Customers ("Felix", 1L);
        this.customersList.add(testCustomers);
        this.testCustomersWithID = new Customers (testCustomers.getName(), testCustomers.getSeatNo());
        this.testCustomersWithID.setCustomersId (id);
        this.customersDTOTest = this.mapToDTO(testCustomersWithID);
    }

    @Test
    public void getAllCustomersTest(){
        when(repository.findAll()).thenReturn(this.customersList);
        when(this.mapper.map(testCustomersWithID, CustomersDTOTest.class)).thenReturn(customersDTOTest);
        assertFalse("Service returned no Customers", this.service.readCustomers().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createCustomersTest(){
        when(repository.save(testCustomers)).thenReturn(testCustomersWithID);
        when(this.mapper.map(testCustomersWithID, CustomersDTOTest.class)).thenReturn(customersDTOTest);
        assertEquals(this.service.createCustomer (testCustomers), this.customersDTOTest);
        verify(repository, times(1)).save(this.testCustomers);
    }

    @Test
    public void findCustomersByIdTest(){
        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testCustomersWithID));
        when(this.mapper.map(testCustomersWithID, CustomersDTOTest.class)).thenReturn(customersDTOTest);
        assertEquals(this.service.findCustomersById (this.id), customersDTOTest);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void deleteCustomersByExistingId(){
        when(this.repository.existsById(id)).thenReturn(true, false);
        assertFalse(service.deleteCustomers(id));
        verify(repository, times(1)).deleteById(id);
        verify(repository, times(2)).existsById(id);
    }

    @Test(expected = CustomersNotFoundException.class)
    public void deleteCustomersByNonExistingId(){
        when(this.repository.existsById(id)).thenReturn(false);
        service.deleteCustomers (id);
        verify(repository, times(1)).existsById(id);
    }
}
