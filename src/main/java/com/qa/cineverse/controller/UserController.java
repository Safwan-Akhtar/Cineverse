package com.qa.cineverse.controller;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.UserDTO;
import com.qa.cineverse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return ResponseEntity.ok(this.service.readUser ());
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        return new ResponseEntity<>(this.service.createUser (user), HttpStatus.CREATED);
    }

    @GetMapping("/readUserByName/{name}")
    public ResponseEntity<UserDetails> readCustomersByName(@PathVariable String name){
        return ResponseEntity.ok(this.service.loadUserByUsername (name));
    }
}
