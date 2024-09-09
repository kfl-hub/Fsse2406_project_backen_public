package com.fsse2406.fsse2406_project_backend.service;

import com.fsse2406.fsse2406_project_backend.objects.cartItem.domainObject.CartItemResponseData;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import jakarta.transaction.Transactional;

import java.util.List;


public interface CartItemService {


    boolean putCartItem(Integer pid, Integer quantity, String size, FirebaseUserData firebaseUserData);

    List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData);


    Integer getUserCartQuantity(FirebaseUserData firebaseUserData);

    CartItemResponseData updateCartQuantity(Integer pid, Integer quantity, String size, FirebaseUserData firebaseUserData);

    boolean  deleteCartItem(Integer pid, FirebaseUserData firebaseUserData);

    @Transactional
    boolean clearCartItem(Integer pid, String size, FirebaseUserData firebaseUserData);
}
