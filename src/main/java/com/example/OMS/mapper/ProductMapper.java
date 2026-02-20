package com.example.OMS.mapper;

import com.example.OMS.dto.InventoryRequestDto;
import com.example.OMS.dto.InventoryResponseDto;
import com.example.OMS.dto.ProductRequestDto;
import com.example.OMS.dto.ProductResponseDto;
import com.example.OMS.entity.Inventory;
import com.example.OMS.entity.Product;

public class ProductMapper {

    public static ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setExpirationDate(product.getExpirationDate());
        dto.setManufacturingDate(product.getManufacturingDate());

        if (product.getInventory() != null) {
            dto.setInventory(toInventoryDto(product.getInventory()));
        }


        return dto;
    }

    private static InventoryResponseDto toInventoryDto(Inventory inventory) {
        InventoryResponseDto dto = new InventoryResponseDto();
        dto.setProductId(inventory.getProductId());
        dto.setProductName(inventory.getProduct().getProductName());
        dto.setQuantity(inventory.getQuantity());
        return dto;
    }
}
