package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTO;
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

    private CustomersDTO customersDTO;

    private CustomersDTO mapToDTO(Customers customers){
        return this.mapper.map(customers, CustomersDTO.class);
    }

    @Before
    public void setUp(){
        this.customersList = new ArrayList<> ();
        this.testCustomers = new Customers ("Felix");
        this.customersList.add(testCustomers);
        this.testCustomersWithID = new Customers (testCustomers.getName());
        this.testCustomersWithID.setCustomerId (id);
        this.customersDTO = this.mapToDTO(testCustomersWithID);
    }

    @Test
    public void getAllCustomersTest(){
        when(repository.findAll()).thenReturn(this.customersList);
        when(this.mapper.map(testCustomersWithID, CustomersDTO.class)).thenReturn(customersDTO);
        assertFalse("Service returned no Customers", this.service.readCustomers().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createCharacterTest(){
        when(repository.save(testCharacterSheet)).thenReturn(testCharacterSheetWithID);
        when(this.mapper.map(testCharacterSheetWithID, CharacterDTO.class)).thenReturn(characterDTO);
        assertEquals(this.service.createCharacter (testCharacterSheet), this.characterDTO);
        verify(repository, times(1)).save(this.testCharacterSheet);
    }

}
