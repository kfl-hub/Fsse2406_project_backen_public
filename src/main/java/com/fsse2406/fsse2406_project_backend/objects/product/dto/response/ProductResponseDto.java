package com.fsse2406.fsse2406_project_backend.objects.product.dto.response;


import com.fsse2406.fsse2406_project_backend.objects.product.data.response.ProductResponseData;

import java.math.BigDecimal;

public class ProductResponseDto {

    private Integer pid;

    private String name;

    private String description;

    private String imageUrl;

    private BigDecimal price;

    private Integer stock;
    private String category;

    public ProductResponseDto() {
    }

    public ProductResponseDto(ProductResponseData data) {
        this.description = data.getDescription();
        this.stock = data.getStock();
        this.imageUrl = data.getImageUrl();
        this.name = data.getName();
        this.pid = data.getPid();
        this.price = data.getPrice();
        this.category=data.getCategory();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
