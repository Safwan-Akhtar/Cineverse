package com.qa.cineverse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTOTest;
import com.qa.cineverse.repo.CustomersRepo;
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
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private CustomersRepo repository;

    @Autowired
    private ModelMapper mapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Customers testCustomers;

    private Customers testCustomersWithID;

    private long id;

    private CustomersDTOTest customersDTOTest;

    private CustomersDTOTest mapToDTO(Customers customers){
        return this.mapper.map(customers, CustomersDTOTest.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testCustomers = new Customers ("Chris");
        this.testCustomersWithID = this.repository.save(testCustomers);
        this.id = testCustomersWithID.getCustomersId ();
        this.customersDTOTest = this.mapToDTO(testCustomersWithID);
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        List<CustomersDTOTest> customersDTOTestList = new ArrayList<> ();
        customersDTOTestList.add(customersDTOTest);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllCustomers")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(customersDTOTestList));
    }

    @Test
    public void getCustomerByID() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getCustomerById/" + this.id)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(customersDTOTest));
    }

    @Test
    public void createCustomerTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testCustomers))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(customersDTOTest));
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        this.mock.perform(
                request(HttpMethod.DELETE, "/deleteCustomer/" + this.id)
        ).andExpect(status().isNoContent());
    }
}
