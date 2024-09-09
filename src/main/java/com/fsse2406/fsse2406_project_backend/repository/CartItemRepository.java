package com.fsse2406.fsse2406_project_backend.repository;

import com.fsse2406.fsse2406_project_backend.objects.cartItem.entity.CartItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity,Integer> {
    List<CartItemEntity> findAllByUser_Uid (Integer uid);
    Optional<CartItemEntity> findByUser_UidAndProduct_pidAndSize (Integer uid, Integer pid,String size);
    Optional<CartItemEntity> findByUser_UidAndCid (Integer uid, Integer cid);
    Integer deleteByUser_firebaseUidAndCid (String firebaseUid, Integer cid);
    Integer deleteByUser_firebaseUidAndProduct_pidAndSize (String firebaseUid, Integer pid,String size);
    Integer countByUser_firebaseUid (String firebaseUid);
}
