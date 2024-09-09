package com.fsse2406.fsse2406_project_backend.objects.cartItem.dto;

import com.fsse2406.fsse2406_project_backend.objects.cartItem.domainObject.CartItemResponseData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CartItemResponseDto {
    private Integer cid;
    private Integer pid;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private Integer cartQuantity;

    private Integer stock;
    private String size;
    private String category;


    public CartItemResponseDto(CartItemResponseData data) {
        this.cid = data.getCid();
        this.cartQuantity = data.getCartQuantity();
        this.imageUrl = data.getProduct().getImageUrl();
        this.name = data.getProduct().getName();
        this.pid = data.getProduct().getPid();
        this.price = data.getProduct().getPrice();
        this.stock = data.getProduct().getStock();
        this.size = data.getSize();
        this.category = data.getProduct().getCategory();
    }

}
