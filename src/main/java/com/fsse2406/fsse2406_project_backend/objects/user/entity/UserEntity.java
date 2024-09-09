package com.fsse2406.fsse2406_project_backend.objects.user.entity;

import com.fsse2406.fsse2406_project_backend.objects.cartItem.entity.CartItemEntity;
import com.fsse2406.fsse2406_project_backend.objects.transaction.entity.TransactionEntity;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user",indexes = @Index(columnList = "firebaseUid"))
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer uid;

    @Column(name = "firebase_uid",nullable = false,unique = true)
    private String firebaseUid;

    @Column(nullable = false,unique = true)
    private String email;

    @OneToMany//(mappedBy = "user")
    private List<CartItemEntity> cartItemEntityList=new ArrayList<>();

    @OneToMany//(mappedBy = "user")
    private List<TransactionEntity> transactionEntityList=new ArrayList<>();

    public UserEntity(FirebaseUserData data) {
        this.firebaseUid = data.getFirebaseUid();
        this.email=data.getEmail();
    }

    public UserEntity() {
    }

    public List<CartItemEntity> getCartItemEntityList() {
        return cartItemEntityList;
    }

    public void setCartItemEntityList(List<CartItemEntity> cartItemEntityList) {
        this.cartItemEntityList = cartItemEntityList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
