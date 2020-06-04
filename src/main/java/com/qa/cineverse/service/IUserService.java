package com.qa.cineverse.service;

import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.exception.UserAlreadyExistsException;

public interface IUserService {
    UserDTO createUser(UserDTO userDto)
            throws UserAlreadyExistsException;
}