package com.example.OMS.dto;

import java.time.LocalDate;

public class ProductResponseDto {

    private Long productId;
    private String productName;
    private int price;
    private LocalDate manufacturingDate;
    private LocalDate expirationDate;
    private InventoryResponseDto inventory;

    public ProductResponseDto() {

    }

    public InventoryResponseDto getInventory() {
        return inventory;
    }

    public void setInventory(InventoryResponseDto inventory) {
        this.inventory = inventory;
    }

    public ProductResponseDto(Long productId, String productName, int price, LocalDate manufacturingDate, LocalDate expirationDate) {
        this.productId=productId;
        this.productName = productName;
        this.price = price;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
