package com.example.OMS.mapper;

import java.util.List;

import com.example.OMS.dto.OrderItemResponseDto;
import com.example.OMS.dto.OrderResponseDto;
import com.example.OMS.entity.Order;
import com.example.OMS.entity.OrderItem;

public class OrderMapper {

    public static OrderResponseDto toDto(Order order) {

        List<OrderItemResponseDto> items =
                order.getOrderItems()
                        .stream()
                        .map(OrderMapper::toItemDto)
                        .toList();

        return new OrderResponseDto(
                order.getOrderId(),
                order.getCreatedAt(),
                order.getStatus(),
                items
        );
    }

    private static OrderItemResponseDto toItemDto(OrderItem item) {
        return new OrderItemResponseDto(
                item.getOrderItemId(),
                item.getProduct().getProductId(),
                item.getProduct().getProductName(),
                item.getQuantity(),
                item.getPrice()
        );
    }
}
