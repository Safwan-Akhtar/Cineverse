package com.qa.cineverse.service;

import com.qa.cineverse.domain.Orders;
import com.qa.cineverse.dto.OrdersDTO;
import com.qa.cineverse.exception.OrdersNotFoundException;
import com.qa.cineverse.repo.OrdersRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final OrdersRepo repo;

    private final ModelMapper mapper;

    @Autowired
    public OrdersService(OrdersRepo repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private OrdersDTO mapToDTO(Orders orders) { return this.mapper.map(orders, OrdersDTO.class); }

    public Set<OrdersDTO> readOrders() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toSet());
    }

    public OrdersDTO createOrders(Orders orders) {
        Orders tempOrders = this.repo.save(orders);
        return this.mapToDTO (tempOrders);
    }

    public OrdersDTO findOrdersById(Long id) {
        return this.mapToDTO(this.repo.findById(id)
                .orElseThrow(OrdersNotFoundException::new));
    }

    public boolean deleteOrders(Long id) {
        if(!this.repo.existsById(id)) {
            throw new OrdersNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }



}
