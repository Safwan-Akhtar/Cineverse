package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepo repository;

    @Mock
    private ModelMapper mapper;

    private List<User> userList;

    private User testUser;

    private final long id = 1L;

    private User testUserWithID;

    private UserDTO userDTO;

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        this.userList = new ArrayList<> ();
        this.testUser = new User (1L, "Boyman", "root",
                "root", "Chris", "Christianson", "test@aol.com",
                true, "ROLE_USER");
        this.userList.add(testUser);
        this.testUserWithID = new User (testUser.getUserId(),testUser.getUsername(), testUser.getPassword(),
                testUser.getMatchingPassword(), testUser.getForename(), testUser.getSurname(), testUser.getEmail(),
                testUser.isActive(), testUser.getRoles());
        this.testUserWithID.setUserId (id);
        this.userDTO = this.mapToDTO(testUserWithID);
    }

    @Test
    public void getAllUserTest(){
        when(repository.findAll()).thenReturn(this.userList);
        when(this.mapper.map(testUserWithID, UserDTO.class)).thenReturn(userDTO);
        assertFalse("Service returned no Users", this.service.readUser().isEmpty());
        verify(repository, times(1)).findAll();
    }

}
