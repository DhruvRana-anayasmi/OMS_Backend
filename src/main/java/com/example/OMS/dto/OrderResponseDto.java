package com.example.OMS.dto;
import java.util.List;

public class OrderResponseDto {

    private long orderId;
    private String status;
    private List<OrderItemResponseDto> items;

    public OrderResponseDto(
            Long orderId,
            String status,
            List<OrderItemResponseDto> items
    ) {
        this.orderId = orderId;
        this.status = status;
        this.items = items;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponseDto> items) {
        this.items = items;
    }
}
