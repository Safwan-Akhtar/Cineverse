package com.qa.cineverse.service;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException ("The username '" + userName + "' does not exist"));

        return user.map(UserDTO::new).get();
    }


}
