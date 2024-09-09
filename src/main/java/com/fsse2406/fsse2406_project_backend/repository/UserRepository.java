package com.fsse2406.fsse2406_project_backend.repository;

import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    Optional<UserEntity> findByFirebaseUid(String firebaseUid);
}
