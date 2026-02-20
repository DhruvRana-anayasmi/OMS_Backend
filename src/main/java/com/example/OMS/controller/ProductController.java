package com.example.OMS.controller;
import com.example.OMS.dto.ProductRequestDto;
import com.example.OMS.dto.ProductResponseDto;
import com.example.OMS.entity.Product;
import com.example.OMS.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("add")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDto createProduct(@RequestBody Product product, Authentication authentication){
        String username=authentication.getName();
        System.out.println(username);
        return service.createProduct(product,username);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("products")
    public List<ProductResponseDto> getProducts(){
        return service.getProducts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("product/{productId}")
    public ProductResponseDto updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDto product, Authentication authentication){

        String username=authentication.getName();
        return service.updateProduct(productId,product,username);
    }

    @DeleteMapping("delete/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long productId){
        return service.deleteProduct(productId);
    }
}
