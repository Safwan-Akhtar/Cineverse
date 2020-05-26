package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomersController {

    private final CustomersService service;

    @Autowired
    public CustomersController(CustomersService service) {
        this.service = service;
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomersDTO>> getAllCustomers(){
        return ResponseEntity.ok(this.service.readCustomers ());
    }

    @PostMapping("/createCustomers")
    public ResponseEntity<CustomersDTO> createCharacter(@RequestBody Customers customers){
        return new ResponseEntity<>(this.service.createCustomers (customers), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCustomers/{id}")
    public ResponseEntity<?> deleteCustomers(@PathVariable Long id){
        return this.service.deleteCustomers (id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getCharacterById/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findCharacterById (id));
    }
}
