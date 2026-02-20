package com.example.OMS.service;

import com.example.OMS.dto.OrderRequestDto;
import com.example.OMS.dto.OrderResponseDto;
import com.example.OMS.entity.*;
import com.example.OMS.exception.InsufficientQuantityException;
import com.example.OMS.exception.InventoryNotFoundException;
import com.example.OMS.exception.UserNotFoundException;
import com.example.OMS.mapper.OrderMapper;
import com.example.OMS.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository repo;

    @Autowired
    OrderItemRepository OrderItemRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    InventoryRepository inventoryRepo;

    @Autowired
    ProductRepository productRepo;


    public OrderResponseDto createOrder(OrderRequestDto request, String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        Order order = new Order();
        order.setStatus("CREATED");
        order.setCreatedBy(user.getUserId());
        order.setUpdatedBy(user.getUserId());
        order.setUpdatedAt(LocalDateTime.now());

            List<OrderItem> items = request.getItems().stream().map(req -> {

                Product product = productRepo.findById(req.getProductId())
                        .orElseThrow(() -> new InventoryNotFoundException("Product not found"));

                Inventory inventory=inventoryRepo.findById(req.getProductId()).orElseThrow(()->new InventoryNotFoundException("Inventory not found"));
                if(inventory.getQuantity()<req.getQuantity()){
                    throw new InsufficientQuantityException("Insufficient Inventory");
                }
                inventory.setQuantity(inventory.getQuantity()-req.getQuantity());
                inventoryRepo.save(inventory);

                OrderItem item = new OrderItem();
                item.setOrder(order);
                item.setProduct(product);
                item.setQuantity(req.getQuantity());
                item.setPrice(product.getPrice() * req.getQuantity());

                return item;
            }).toList();

            order.setOrderItems(items);

            Order savedOrder = repo.save(order);
        return OrderMapper.toDto(savedOrder);
    }

    public List<OrderResponseDto> getOrders(String username) {
        User user=userRepo.findByUsername(username);
        List <Order> orders=repo.findOrderByCreatedBy(user.getUserId());
        List<OrderResponseDto> show=orders.stream().map((req)->{
            OrderResponseDto dto=OrderMapper.toDto(req);
            return dto;
        }).toList();
        return show;
    }
}
