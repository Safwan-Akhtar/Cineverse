package com.qa.cineverse.service;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.exception.UserAlreadyExistsException;

public interface IUserService {
    User registerNewUserAccount(UserDTO userDto)
            throws UserAlreadyExistsException;
}