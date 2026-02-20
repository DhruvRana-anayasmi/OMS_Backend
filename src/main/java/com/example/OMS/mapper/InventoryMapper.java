package com.example.OMS.mapper;

import com.example.OMS.dto.InventoryResponseDto;
import com.example.OMS.entity.Inventory;

public class InventoryMapper {

    public static InventoryResponseDto toDto(Inventory inventory) {
        InventoryResponseDto dto = new InventoryResponseDto();
        dto.setProductId(inventory.getProduct().getProductId());
        dto.setProductName(inventory.getProduct().getProductName());
        dto.setQuantity(inventory.getQuantity());
        return dto;
    }
}

