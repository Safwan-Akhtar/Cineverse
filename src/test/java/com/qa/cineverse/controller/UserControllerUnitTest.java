package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.CustomersDTOTest;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.service.CustomersService;
import com.qa.cineverse.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService service;

    private List<User> user;

    private User testUser;

    private User testUserWithId;

    private final long id = 1L;

    private UserDTO userDTO;

    private final ModelMapper mapper = new ModelMapper();

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        this.user = new ArrayList<> ();
        this.testUser = new User (1L, "Boyman", "root",
                "root", "Chris", "Christianson", "test@aol.com",
                true, "ROLE_USER");
        this.user.add(testUser);
        this.testUserWithId = new User (testUser.getUserId(),testUser.getUsername(), testUser.getPassword(),
                testUser.getMatchingPassword(), testUser.getForename(), testUser.getSurname(), testUser.getEmail(),
                testUser.isActive(), testUser.getRoles());
        this.testUserWithId.setUserId (this.id);
        this.userDTO = this.mapToDTO(testUserWithId);
    }

    @Test
    public void getAllUserTest(){
        when(service.readUser()).thenReturn(this.user.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No customer found", this.userController.getAllUser().getBody().isEmpty());
        verify(service, times(1)).readUser();
    }

    @Test
    public void createUserTest(){
        when(this.service.createUser(userDTO)).thenReturn(this.userDTO);
        assertEquals(this.userController.createUser(userDTO), new ResponseEntity<UserDTO> (this.userDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createUser(userDTO);
    }


}
