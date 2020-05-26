package com.qa.cineverse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTO;
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

    private CustomersDTO customersDTO;

    private CustomersDTO mapToDTO(Customers customers){
        return this.mapper.map(customers, CustomersDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testCustomers = new Customers ("Chris");
        this.testCustomersWithID = this.repository.save(testCustomers);
        this.id = testCustomersWithID.getCustomerId ();
        this.customersDTO = this.mapToDTO(testCustomersWithID);
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        List<CustomersDTO> customersDTOList = new ArrayList<> ();
        customersDTOList.add(customersDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllCustomers")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(customersDTOList));
    }


}
