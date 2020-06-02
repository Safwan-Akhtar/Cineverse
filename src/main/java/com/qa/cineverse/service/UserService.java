package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.exception.UserAlreadyExistsException;
import com.qa.cineverse.repo.CustomersRepo;
import com.qa.cineverse.repo.TicketsRepo;
import com.qa.cineverse.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepo repository;


    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepo repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private UserDTO mapToDTO(User user) {
        return this.mapper.map(user, UserDTO.class);
    }

    public List<UserDTO> readUser(){
        return this.repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public UserDTO createUser(UserDTO userDTO){
        User user = new User();
        user.setForename(userDTO.getForename());
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setMatchingPassword(userDTO.getMatchingPassword());
        user.setActive(userDTO.isEnabled ());
        user.setEmail(userDTO.getEmail());
        user.setRoles("ROLE_USER"); //Doesn't work
        return this.mapToDTO(this.repository.save(user));
    }

    private boolean emailExists(final String email) {
        return repository.findByEmail (email) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException ("The username '" + username + "' does not exist"));
        return user.map(UserDTO::new).get();
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO userDTO)
            throws UserAlreadyExistsException {

        if (emailExist(userDTO.getEmail())) {
            throw new UserAlreadyExistsException(
                    "There is an account with that email address: "
                            +  userDTO.getEmail());
        }

        User user = new User();
        user.setForename(userDTO.getForename());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRoles("ROLE_USER");
        return repository.save(user);
    }

    private boolean emailExist(String email) {
        return repository.findByEmail(email) != null;
    }
}