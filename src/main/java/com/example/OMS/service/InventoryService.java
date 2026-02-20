package com.example.OMS.service;


import com.example.OMS.dto.InventoryResponseDto;
import com.example.OMS.entity.Inventory;
import com.example.OMS.entity.Product;
import com.example.OMS.exception.InsufficientQuantityException;
import com.example.OMS.exception.InventoryNotFoundException;
import com.example.OMS.mapper.InventoryMapper;
import com.example.OMS.mapper.ProductMapper;
import com.example.OMS.repository.InventoryRepository;
import com.example.OMS.repository.ProductRepository;
import com.example.OMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository repo;

    @Autowired
    UserRepository userRepo;
    public Inventory addInventory(Long productId, int quantity, Authentication authentication) {
        String username=authentication.getName();
        Long UserId=userRepo.findByUsername(username).getUserId();
        Inventory inv = repo.findById(productId)
                .orElseThrow(() -> new InventoryNotFoundException("No inventory found"));

        inv.setQuantity(inv.getQuantity() + quantity);
        inv.setUpdatedBy(UserId);
        inv.setUpdatedAt(LocalDateTime.now());

        return repo.save(inv);
    }

    public Inventory removeInventory(Long productId, int quantity ,Authentication authentication) {

        String username=authentication.getName();
        Long UserId=userRepo.findByUsername(username).getUserId();

        Inventory inv = repo.findById(productId)
                .orElseThrow(() -> new InventoryNotFoundException("No inventory found"));

        if (inv.getQuantity() < quantity) {
            throw new InsufficientQuantityException("Insufficient Quantity");
        }

        inv.setUpdatedBy(UserId);
        inv.setUpdatedAt(LocalDateTime.now());
        inv.setQuantity(inv.getQuantity() - quantity);
        return repo.save(inv);
    }

    public List<InventoryResponseDto> getInventory() {
        List <Inventory> invs=repo.findAll();
        List <InventoryResponseDto> invres;

        invres=invs.stream().map((req)->{
            return InventoryMapper.toDto(req);
        }).toList();
        return invres;


    }
}

