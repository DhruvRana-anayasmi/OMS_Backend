package com.example.OMS.service;

import com.example.OMS.dto.ProductRequestDto;
import com.example.OMS.dto.ProductResponseDto;
import com.example.OMS.entity.Inventory;
import com.example.OMS.entity.Product;
import com.example.OMS.entity.User;
import com.example.OMS.exception.InventoryNotFoundException;
import com.example.OMS.exception.UserNotFoundException;
import com.example.OMS.mapper.ProductMapper;
import com.example.OMS.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.OMS.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;
    @Autowired
    UserRepository userRepo;

    public ProductResponseDto createProduct(Product product, String username) {

        User user = userRepo.findByUsername(username);
        if(user==null){
            throw new UserNotFoundException("Auth User not found");
        }
        product.setCreatedBy(user.getUserId());
        product.setUpdatedBy(user.getUserId());
        product.setUpdatedAt(LocalDateTime.now());
        product.setStatus("ACTIVE");

        Inventory inventory = new Inventory();
        inventory.setQuantity(0);
        inventory.setStatus("ACTIVE");
        inventory.setCreatedBy(user.getUserId());
        inventory.setUpdatedBy(user.getUserId());
        inventory.setUpdatedAt(LocalDateTime.now());
        inventory.setProduct(product);
        product.setInventory(inventory);

        Product savedProduct = repo.save(product);

        return ProductMapper.toDto(savedProduct);
    }


    public List<ProductResponseDto> getProducts() {
      List<ProductResponseDto> products=repo.findByStatus("ACTIVE")
              .stream()
              .map(ProductMapper::toDto)
              .toList();
      return products;
    }

    public ProductResponseDto updateProduct(Long productId, ProductRequestDto product, String username){
        Optional<Product> optionalProduct= repo.findById(productId);
        if(optionalProduct.isPresent()){
            Product productToUpdate=optionalProduct.get();
            User user= userRepo.findByUsername(username);
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setManufacturingDate(product.getManufacturingDate());
            productToUpdate.setExpirationDate(product.getExpirationDate());
            productToUpdate.setUpdatedBy(user.getUserId());
            productToUpdate.setUpdatedAt(LocalDateTime.now());

            repo.save(productToUpdate);
            return ProductMapper.toDto(productToUpdate);

        }
        else
        {
            throw new InventoryNotFoundException("Product not found with id "+productId);
        }
    }

    public String deleteProduct(Long productId) {
        Product product=repo.findById(productId).orElseThrow(()->new InventoryNotFoundException("Product not found"));
        product.setStatus("INACTIVE");
        product.getInventory().setStatus("INACTIVE");
        repo.save(product);
        return "Marked as inactive";
    }
}
