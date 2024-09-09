package com.fsse2406.fsse2406_project_backend.repository;

import com.fsse2406.fsse2406_project_backend.objects.transaction.entity.TransactionEntity;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.entity.TransactionProductEntity;
import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity,Integer> {

}
