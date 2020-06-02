package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.repo.CustomersRepo;
import com.qa.cineverse.repo.UserRepo;
import org.junit.Before;
import org.junit.Ignore;
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
public class UserServiceIntegrationTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepo repository;

    @Autowired
    private ModelMapper mapper;

    private User testUser;

    private User testUserWithID;

    private UserDTO userDTO;

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        this.testUser = new User (1L, "Boyman", "root",
                "root", "Chris", "Christianson", "test@aol.com",
                true, "ROLE_USER");
        this.repository.deleteAll();
        this.testUserWithID = this.repository.save(this.testUser);
    }

    @Test
    public void readUserTest(){
        assertThat(this.service.readUser())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testUserWithID)).collect(Collectors.toList())
                );
    }

    @Ignore
    @Test
    public void createCustomersTest(){
        assertEquals(this.mapToDTO(this.testUserWithID), this.service.createUser (userDTO));
    }




}
