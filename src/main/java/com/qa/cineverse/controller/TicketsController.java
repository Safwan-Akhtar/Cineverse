package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.domain.Tickets;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.dto.TicketsDTO;
import com.qa.cineverse.service.CustomersService;
import com.qa.cineverse.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketsController {

    private final TicketsService service;

    @Autowired
    public TicketsController(TicketsService service) {
        this.service = service;
    }

    @GetMapping("/getAllTickets")
    public ResponseEntity<List<TicketsDTO>> getAllTickets(){
        return ResponseEntity.ok(this.service.readTickets ());
    }

    @PostMapping("/createTickets")
    public ResponseEntity<TicketsDTO> createTickets(@RequestBody Tickets tickets){
        return new ResponseEntity<>(this.service.createTickets (tickets), HttpStatus.CREATED);
    }

}
