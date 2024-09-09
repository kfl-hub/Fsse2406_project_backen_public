package com.fsse2406.fsse2406_project_backend.repository;

import com.fsse2406.fsse2406_project_backend.objects.transaction.entity.TransactionEntity;
import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity,Integer> {
List<TransactionEntity> findAllByUser (UserEntity user);
    Optional<TransactionEntity> findByUserAndTid (UserEntity user,Integer tid);
}
