package com.qa.cineverse.controller;

import com.qa.cineverse.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomersController {

    private final CustomersService service;

    @Autowired
    public CustomersController(CustomersService service) {
        this.service = service;
    }


}
