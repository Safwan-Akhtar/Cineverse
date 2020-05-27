package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Orders;
import com.qa.cineverse.dto.OrdersDTO;
import com.qa.cineverse.service.OrdersService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerUnitTest {

    @InjectMocks
    private OrdersController ordersController;

    @Mock
    private OrdersService service;

    private List<Orders> orders;

    private Orders testOrders;

    private Orders testOrdersWithId;

    private final long id = 1L;

    private OrdersDTO ordersDTO;

    private final ModelMapper mapper = new ModelMapper();

    private OrdersDTO mapToDTO(Orders orders) { return this.mapper.map(orders, OrdersDTO.class); }

}
