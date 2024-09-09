package com.fsse2406.fsse2406_project_backend.service.implement;

import com.fsse2406.fsse2406_project_backend.exception.ProductServiceException;
import com.fsse2406.fsse2406_project_backend.exception.TransactionServiceException;
import com.fsse2406.fsse2406_project_backend.objects.cartItem.domainObject.CartItemResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transaction.Status;
import com.fsse2406.fsse2406_project_backend.objects.transaction.domainObject.TransactionResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transaction.entity.TransactionEntity;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.entity.TransactionProductEntity;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import com.fsse2406.fsse2406_project_backend.repository.TransactionProductRepository;
import com.fsse2406.fsse2406_project_backend.repository.TransactionRepository;
import com.fsse2406.fsse2406_project_backend.service.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private UserService userService;
    private CartItemService cartItemService;
    private ProductService productService;
    private TransactionRepository transactionRepository;
    private TransactionProductRepository transactionProductRepository;
    private final TransactionProductService transactionProductService;

    public TransactionServiceImpl(ProductService productService, CartItemService cartItemService,
                                  TransactionProductRepository transactionProductRepository,
                                  TransactionRepository transactionRepository, UserService userService,
                                  TransactionProductService transactionProductService) {
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.transactionProductRepository = transactionProductRepository;
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.transactionProductService = transactionProductService;
    }

    @Override
    @Transactional
    public TransactionResponseData CreateTransaction(FirebaseUserData firebaseUserData) {
        UserEntity user = userService.getEntityByFirebaseUserData(firebaseUserData);
        TransactionEntity newTransaction = transactionRepository.save(new TransactionEntity(user));
        List<CartItemResponseData> cartItemResDataList = cartItemService.getUserCart(firebaseUserData);
        List<TransactionProductEntity> tpEntityList = new ArrayList<>();
        try {
            if (cartItemResDataList.isEmpty()) {
                throw new TransactionServiceException("Prepare Transaction-No cart item");
            }

            for (CartItemResponseData cartItem : cartItemResDataList) {
                tpEntityList.add(transactionProductService.addTransactionProduct(newTransaction, cartItem));
//BigDecimal subtotal = cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getCartQuantity()));
                newTransaction.addTotal(cartItem.getProduct().getPrice().
                        multiply(BigDecimal.valueOf(cartItem.getCartQuantity())));
            }
            newTransaction.setItems(tpEntityList);
            return new TransactionResponseData(transactionRepository.save(newTransaction));

        } catch (TransactionServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public List<TransactionResponseData> getALlTransaction(FirebaseUserData firebaseUserData) {
        UserEntity user = userService.getEntityByFirebaseUserData(firebaseUserData);
        List<TransactionEntity> tList = transactionRepository.findAllByUser(user);
        List<TransactionResponseData> tResDataList = new ArrayList<>();
        try {
            if (tList.isEmpty()) {
                throw new TransactionServiceException("No transaction found-Uid: " + user.getUid());
            }
            for (TransactionEntity transaction : tList) {
                tResDataList.add(new TransactionResponseData(transaction));
            }
            return tResDataList;
        } catch (TransactionServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid) {
        UserEntity user = userService.getEntityByFirebaseUserData(firebaseUserData);
        try {
            return new TransactionResponseData(transactionRepository.findByUserAndTid(user, tid).
                    orElseThrow(() -> new TransactionServiceException("Transaction not found-tid: " + tid)));
        } catch (TransactionServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        }
    }

    @Override
    @Transactional
    public boolean payForTransaction(FirebaseUserData firebaseUserData, Integer tid) {
        UserEntity user = userService.getEntityByFirebaseUserData(firebaseUserData);
        try {
            TransactionEntity transaction = transactionRepository.findByUserAndTid(user, tid).
                    orElseThrow(() -> new TransactionServiceException(
                            "Pay- Transaction not found tid:" + tid));

            if (!transaction.getStatus().equals(Status.PREPARE)) {
                throw new TransactionServiceException(String.format(
                        "Pay Transaction can not be proceed tid:%d is %S", tid, transaction.getStatus()));
            }
            for (TransactionProductEntity item : transaction.getItems()) {
                productService.adjustProductQuantity(
                        item.getPid(),
                        item.getQuantity());
            }
            transaction.setStatus(Status.PROCESSING);
            return true;
        } catch (TransactionServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        } catch (ProductServiceException exception) {
            logger.warn("Pay for Transaction -Deduce Products error");
            throw exception;
        }
    }

    @Override
    @Transactional
    public TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData, Integer tid) {
        UserEntity user = userService.getEntityByFirebaseUserData(firebaseUserData);
        try {
            TransactionEntity transaction = transactionRepository.findByUserAndTid(user, tid).
                    orElseThrow(() -> new TransactionServiceException(
                            "Finish Transaction-Not found tid:" + tid));
            if (!transaction.getStatus().equals(Status.PROCESSING)) {
                throw new TransactionServiceException(String.format(
                        "Finish Transaction can not be proceed tid:%d is %S", tid, transaction.getStatus()));
            }
            for (TransactionProductEntity item : transaction.getItems()) {
                cartItemService.clearCartItem(item.getPid(), item.getSize(),firebaseUserData);
            }
            transaction.setStatus(Status.SUCCESS);

            return new TransactionResponseData(transactionRepository.save(transaction));
        } catch (TransactionServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        }
    }

}
