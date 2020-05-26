package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.repo.CustomersRepo;
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
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomersServiceIntegrationTest {

    @Autowired
    private CustomersService service;

    @Autowired
    private CustomersRepo repository;

    @Autowired
    private ModelMapper mapper;

    private Customers testCustomers;

    private Customers testCustomersWithID;

    private CustomersDTO mapToDTO(Customers customers){
        return this.mapper.map(customers, CustomersDTO.class);
    }

    @Before
    public void setUp(){
        this.testCustomers = new Customers ("Caroline");
        this.repository.deleteAll();
        this.testCustomersWithID = this.repository.save(this.testCustomers);
    }

    @Test
    public void readCustomersTest(){
        assertThat(this.service.readCustomers())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testCustomersWithID)).collect(Collectors.toList())
                );
    }

    @Test
    public void createCustomersTest(){
        assertEquals(this.mapToDTO(this.testCustomersWithID), this.service.createCustomer (testCustomers));
    }

    @Test
    public void findCustomersByIdTest(){
        assertThat(this.service.findCustomersById (this.testCustomersWithID.getCustomerId ())).isEqualTo(this.mapToDTO(this.testCustomersWithID));
    }

    @Test
    public void deleteCustomersTest(){
        assertThat(this.service.deleteCustomers(this.testCustomersWithID.getCustomerId ())).isFalse();
    }

}
