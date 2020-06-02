package com.qa.cineverse.service;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MyUserService implements UserDetailsService  {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ModelMapper mapper;

    @Autowired
    public MyUserService(UserRepo userRepo, ModelMapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    private UserDTO mapToDTO(User user) {
        return this.mapper.map(user, UserDTO.class);
    }

    private boolean emailExists(final String email) {
        return userRepo.findByEmail (email) != null;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException ("The username '" + username + "' does not exist"));
        return user.map(UserDTO::new).get();
    }

//    @Override
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//
//        User user = userRepo.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException(
//                    "No user found with username: "+ email);
//        }
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        return  new org.springframework.security.core.userdetails.User
//                (user.getEmail(),
//                        user.getPassword().toLowerCase(), enabled, accountNonExpired,
//                        credentialsNonExpired, accountNonLocked,
//                        getAuthorities(user));
//    }
//
//    private static List<GrantedAuthority> getAuthorities (User user) {
//        List<GrantedAuthority> authorities = Arrays.stream (user.getRoles ().split (","))
//                .map (SimpleGrantedAuthority::new)
//                .collect (Collectors.toList ());
//        return authorities;
//    }
}

