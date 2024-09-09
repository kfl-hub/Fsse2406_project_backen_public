package com.fsse2406.fsse2406_project_backend.service.implement;

import com.fsse2406.fsse2406_project_backend.objects.cartItem.domainObject.CartItemResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transaction.entity.TransactionEntity;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.entity.TransactionProductEntity;
import com.fsse2406.fsse2406_project_backend.repository.TransactionProductRepository;
import com.fsse2406.fsse2406_project_backend.service.TransactionProductService;

import org.springframework.stereotype.Service;

@Service

public class TransactionalProductServiceImpl implements TransactionProductService {
    private final TransactionProductRepository transactionProductRepository;

    public TransactionalProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public TransactionProductEntity addTransactionProduct(TransactionEntity transaction, CartItemResponseData cartItem) {

        return transactionProductRepository.save(
                new TransactionProductEntity(transaction, cartItem));
    }
}
