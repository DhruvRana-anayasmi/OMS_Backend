package com.example.OMS.dto;

import java.time.LocalDate;

public class ProductRequestDto {

    private String productName;
    private int price;
    private LocalDate manufacturingDate;
    private LocalDate expirationDate;

    public ProductRequestDto() {
    }

    public ProductRequestDto(Long productId, String productName, int price, LocalDate manufacturingDate, LocalDate expirationDate) {

        this.productName = productName;
        this.price = price;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
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
