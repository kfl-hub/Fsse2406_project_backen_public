package com.fsse2406.fsse2406_project_backend.objects.product.entity;

import com.fsse2406.fsse2406_project_backend.objects.cartItem.entity.CartItemEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product",indexes = @Index(columnList = "pid"))

public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 1000)
    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private String category;

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity> cartItemList;

    public ProductEntity(String description, String imageUrl, String name, Integer pid, BigDecimal price, Integer stock,String category) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.name = name;
        this.pid = pid;
        this.price = price;
        this.stock = stock;
        this.category=category;
    }

    public ProductEntity() {
    }

    public List<CartItemEntity> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemEntity> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public boolean stockOut(Integer quantity) {
        if (this.stock >= quantity) {
            this.stock -= quantity;
            return true;
        }else {
            return false;
        }
    }
}
