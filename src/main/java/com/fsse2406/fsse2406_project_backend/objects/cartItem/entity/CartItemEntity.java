package com.fsse2406.fsse2406_project_backend.objects.cartItem.entity;

import com.fsse2406.fsse2406_project_backend.objects.product.entity.ProductEntity;
import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_item",indexes = @Index(columnList = "cid"))
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = true)
    private String size;


    public CartItemEntity(UserEntity user, ProductEntity product, Integer quantity,String size) {
        this.user = user;
        this.quantity = quantity;
        this.product = product;
        this.size=size;

    }

    public CartItemEntity(UserEntity user,ProductEntity product) {
        this.user = user;
        this.product=product;
    }

}
