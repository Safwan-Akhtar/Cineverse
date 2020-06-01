package com.qa.cineverse.service;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.exception.UserAlreadyExistsException;
import com.qa.cineverse.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo repository;

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