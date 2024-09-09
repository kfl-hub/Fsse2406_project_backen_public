package com.fsse2406.fsse2406_project_backend.objects.cartItem.domainObject;

import com.fsse2406.fsse2406_project_backend.objects.cartItem.entity.CartItemEntity;
import com.fsse2406.fsse2406_project_backend.objects.product.data.response.ProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.UserResponseData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemResponseData {

    private Integer cid;

    private ProductResponseData product;

    private Integer cartQuantity;

    private UserResponseData user;

    private String size;


    public CartItemResponseData(CartItemEntity entity) {
        this.cid = entity.getCid();
        this.product = new ProductResponseData(entity.getProduct());
        this.cartQuantity = entity.getQuantity();
        this.user = new UserResponseData(entity.getUser());
        this.size = entity.getSize();

    }


}
