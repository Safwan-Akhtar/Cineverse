package com.qa.cineverse.service;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.exception.UserAlreadyExistsException;
import com.qa.cineverse.validation.EmailValidator;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException ("The username '" + userName + "' does not exist"));
        return user.map(UserDTO::new).get();
    }

//    public UserDetails loadUserByEmail(String email)
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
//                        getAuthorities());
//    }

    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<> ();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority (role));
        }
        return authorities;
    }


}
