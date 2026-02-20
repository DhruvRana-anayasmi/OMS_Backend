package com.example.OMS.controller;

import com.example.OMS.dto.OrderRequestDto;
import com.example.OMS.dto.OrderResponseDto;
import com.example.OMS.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping("order")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto order, Authentication authentication){
        String username=authentication.getName();
        return service.createOrder(order,username);
    }

    @GetMapping("orders")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<OrderResponseDto> viewHistory(Authentication authentication){
        String username=authentication.getName();
        return service.getOrders(username);
    }
}
