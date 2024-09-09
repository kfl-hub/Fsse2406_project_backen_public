package com.fsse2406.fsse2406_project_backend.service;


import com.fsse2406.fsse2406_project_backend.objects.cartItem.domainObject.CartItemResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transaction.domainObject.TransactionResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transaction.entity.TransactionEntity;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.entity.TransactionProductEntity;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;

import java.util.List;

public interface TransactionProductService {


    TransactionProductEntity addTransactionProduct (TransactionEntity transaction, CartItemResponseData cartItem);
}
