package com.qa.cineverse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.repo.CustomersRepo;
import com.qa.cineverse.repo.UserRepo;
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
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private UserRepo repository;

    @Autowired
    private ModelMapper mapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private User testUser;

    private User testCustomersWithID;

    private long id;

    private UserDTO userDTO;

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testUser = new User (1L, "Boyman", "root",
                "root", "Chris", "Christianson", "test@aol.com",
                true, "ROLE_USER");
        this.testCustomersWithID = this.repository.save(testUser);
        this.id = testCustomersWithID.getUserId ();
        this.userDTO = this.mapToDTO(testCustomersWithID);
    }

    @Test
    public void getAllUserTest() throws Exception {
        List<UserDTO> userDTOList = new ArrayList<> ();
        userDTOList.add(userDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllUser")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(userDTOList));
    }

    @Test
    public void createUserTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testUser))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(userDTO));
    }

}
