package com.qa.cineverse.controller;

import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.exception.EmailExistsException;
import com.qa.cineverse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

//    @PostMapping("/registerNewUserAccount")
//    public ResponseEntity<UserDTO> registerNewUserAccount(@RequestBody User accountDTO) throws EmailExistsException {
//        return new ResponseEntity<User> (this.service.registerNewUserAccount (accountDTO), HttpStatus.CREATED);
//    }


}
