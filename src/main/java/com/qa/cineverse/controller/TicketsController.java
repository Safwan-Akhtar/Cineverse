package com.qa.cineverse.controller;

import com.qa.cineverse.dto.TicketsDTO;
import com.qa.cineverse.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @DeleteMapping("/deleteTicket/{id}")
    public ResponseEntity<TicketsDTO> deleteTicket(@PathVariable Long id){
        return this.service.deleteTicket (id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }
}
