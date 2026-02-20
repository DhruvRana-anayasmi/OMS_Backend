package com.example.OMS.controller;
import com.example.OMS.dto.InventoryRequestDto;
import com.example.OMS.dto.InventoryResponseDto;
import com.example.OMS.entity.Inventory;
import com.example.OMS.mapper.InventoryMapper;
import com.example.OMS.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//
@RestController
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    InventoryService service;

    @GetMapping("inventory")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<InventoryResponseDto> viewInventory(){
        return service.getInventory();
    }

    @PostMapping("/increase/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponseDto increaseInventory(@PathVariable Long productId, @RequestBody InventoryRequestDto request, Authentication authentication) {
        Inventory inventory = service.addInventory(productId, request.getQuantity(),authentication);
        return InventoryMapper.toDto(inventory);
    }

    @PostMapping("/decrease/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponseDto decreaseInventory(@PathVariable Long productId,@RequestBody InventoryRequestDto request,  Authentication authentication) {
        Inventory inventory = service.removeInventory(productId, request.getQuantity(),authentication);
        return InventoryMapper.toDto(inventory);
    }
}

