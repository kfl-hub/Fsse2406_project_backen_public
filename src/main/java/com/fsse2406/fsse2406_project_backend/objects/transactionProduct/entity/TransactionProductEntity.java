package com.fsse2406.fsse2406_project_backend.objects.transactionProduct.entity;

import com.fsse2406.fsse2406_project_backend.objects.cartItem.domainObject.CartItemResponseData;
import com.fsse2406.fsse2406_project_backend.objects.cartItem.entity.CartItemEntity;
import com.fsse2406.fsse2406_project_backend.objects.product.data.response.ProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.product.entity.ProductEntity;
import com.fsse2406.fsse2406_project_backend.objects.transaction.entity.TransactionEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "transaction_product")
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;

    @Column(nullable = false)
    private Integer pid;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 500)
    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = true)
    private String size;

    @ManyToOne
    @JoinColumn(name = "tid", referencedColumnName = "tid")
    private TransactionEntity transaction;


    public TransactionProductEntity(TransactionEntity transaction, CartItemResponseData cartItem) {
        ProductResponseData product = cartItem.getProduct();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.name = product.getName();
        this.pid = product.getPid();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.quantity = cartItem.getCartQuantity();
        this.size = cartItem.getSize();
        this.transaction = transaction;
    }

}
