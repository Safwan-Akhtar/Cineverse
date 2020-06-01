package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.exception.EmailExistsException;
import com.qa.cineverse.repo.CustomersRepo;
import com.qa.cineverse.repo.TicketsRepo;
import com.qa.cineverse.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepo userRepo, ModelMapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    private UserDTO mapToDTO(User user) {
        return this.mapper.map(user, UserDTO.class);
    }

    private boolean emailExists(final String email) {
        return userRepo.findByEmail (email).isPresent ();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException ("The username '" + userName + "' does not exist"));
        return user.map(UserDTO::new).get();
    }

    public UserDTO registerNewUserAccount(User user){
        return this.mapToDTO(this.userRepo.save(user));
    }



//    public User registerNewUserAccount(UserDTO accountDTO) throws EmailExistsException {
//        if (emailExists(accountDTO.getEmail())) {
//            throw new EmailExistsException (
//                    "There is an account with that email address:" + accountDTO.getEmail());
//        }
//        User user = new User();
//        user.setForename(accountDTO.getForename());
//        user.setSurname(accountDTO.getSurname());
//        user.setUserName (accountDTO.getUsername());
//        user.setSurname(accountDTO.getSurname());
//
//        user.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
//
//        user.setEmail(accountDTO.getEmail());
//        user.setRoles("ROLE_USER");
//        return userRepo.save(user);
//    }


}
