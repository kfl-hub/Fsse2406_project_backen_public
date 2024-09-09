package com.fsse2406.fsse2406_project_backend.service.implement;

import com.fsse2406.fsse2406_project_backend.exception.CartItemServiceException;
import com.fsse2406.fsse2406_project_backend.exception.ProductServiceException;
import com.fsse2406.fsse2406_project_backend.objects.cartItem.domainObject.CartItemResponseData;
import com.fsse2406.fsse2406_project_backend.objects.cartItem.entity.CartItemEntity;
import com.fsse2406.fsse2406_project_backend.objects.product.entity.ProductEntity;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import com.fsse2406.fsse2406_project_backend.repository.CartItemRepository;
import com.fsse2406.fsse2406_project_backend.repository.UserRepository;
import com.fsse2406.fsse2406_project_backend.service.CartItemService;
import com.fsse2406.fsse2406_project_backend.service.ProductService;
import com.fsse2406.fsse2406_project_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    private static final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    private final UserService userService;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public CartItemServiceImpl(UserService userService, UserRepository userRepository, CartItemRepository cartItemRepository, ProductService productService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }


    @Override
    public boolean putCartItem(Integer pid, Integer quantity, String size, FirebaseUserData firebaseUserData) {
        try {
            if (quantity <= 0) {
                throw new CartItemServiceException("Add item to cart- Quantity must greater than zero- qty: " + quantity);//Or warning not enough stock(+total qty?)
            }
            UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            ProductEntity product = productService.getProductEntityById(pid);
            CartItemEntity cartItem = cartItemRepository.findByUser_UidAndProduct_pidAndSize(
                            userEntity.getUid(),
                            product.getPid(),
                            size).
                    orElse(new CartItemEntity(userEntity, product, 0, size));
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setSize(size);
            cartItemRepository.save(cartItem);
            return true;

        } catch (ProductServiceException exception) {
            logger.warn("Add item to cart- product not found");
            throw exception;
        } catch (CartItemServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData) {
        List<CartItemResponseData> cartItemResponseDataList = new ArrayList<>();
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);

        for (CartItemEntity item : cartItemRepository.findAllByUser_Uid(userEntity.getUid())) {
            cartItemResponseDataList.add(new CartItemResponseData(item));
        }
        return cartItemResponseDataList;
    }

    @Override
    public Integer getUserCartQuantity(FirebaseUserData firebaseUserData) {
        return cartItemRepository.countByUser_firebaseUid(firebaseUserData.getFirebaseUid());
    }

    @Override
    @Transactional
    public CartItemResponseData updateCartQuantity(Integer cid, Integer quantity, String size, FirebaseUserData firebaseUserData) {
        UserEntity user = userService.getEntityByFirebaseUserData(firebaseUserData);
        try {
            CartItemEntity cartItem = cartItemRepository.findByUser_UidAndCid(
                            user.getUid(), cid).
                    orElseThrow(() -> new CartItemServiceException(
                            String.format("Update cart item quantity-Item not found-User: %d,Item: %d", user.getUid(), cid)));
            cartItem.setQuantity(quantity);
            cartItem.setSize(size);
            return new CartItemResponseData(cartItemRepository.save(cartItem));

        } catch (CartItemServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        }
    }

    @Override
    @Transactional
    public boolean deleteCartItem(Integer cid, FirebaseUserData firebaseUserData) {
        try {
            if (cartItemRepository.deleteByUser_firebaseUidAndCid(firebaseUserData.getFirebaseUid(), cid) > 0) {
                return true;
            }
            throw new CartItemServiceException("Delete cart item- item not found- cdi: " + cid);
        } catch (CartItemServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        }
    }

    @Override
    @Transactional
    public boolean clearCartItem(Integer pid, String size, FirebaseUserData firebaseUserData) {
        try {
            if (cartItemRepository.deleteByUser_firebaseUidAndProduct_pidAndSize(firebaseUserData.getFirebaseUid(), pid,size) > 0) {
                return true;
            }
            throw new CartItemServiceException("Delete cart item- item not found- pid: " + pid+"size: "+size);
        } catch (CartItemServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        }
    }
}
