package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Orders;
import com.qa.cineverse.dto.OrdersDTO;
import com.qa.cineverse.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class OrdersController {

    private final OrdersService service;

    @Autowired
    public OrdersController(OrdersService service) {
        this.service = service;
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<Set<OrdersDTO>> getAllOrders(){
        return ResponseEntity.ok(this.service.readOrders ());
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrdersDTO> createOrder(@RequestBody Orders orders){
        return new ResponseEntity<>(this.service.createOrder (orders), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<?> deleteOrders(@PathVariable Long id){
        return this.service.deleteOrders (id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<OrdersDTO> getSkillsById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findOrdersById (id));
    }
}
