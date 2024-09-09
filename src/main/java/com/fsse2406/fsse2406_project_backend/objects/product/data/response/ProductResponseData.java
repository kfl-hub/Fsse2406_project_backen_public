package com.fsse2406.fsse2406_project_backend.objects.product.data.response;

import com.fsse2406.fsse2406_project_backend.objects.product.entity.ProductEntity;

import java.math.BigDecimal;

public class ProductResponseData {

    private Integer pid;

    private String name;

    private String description;

    private String imageUrl;

    private BigDecimal price;

    private Integer stock;

    private String category;

    public ProductResponseData() {
    }

    public ProductResponseData(ProductEntity entity) {
        this.description = entity.getDescription();
        this.stock = entity.getStock();
        this.imageUrl = entity.getImageUrl();
        this.name = entity.getName();
        this.pid = entity.getPid();
        this.price = entity.getPrice();
        this.category=entity.getCategory();
    }

    public ProductResponseData(Integer pid, String name, String description, String imageUrl, BigDecimal price, int stock) {
        this.pid = pid;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.category="default";
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
