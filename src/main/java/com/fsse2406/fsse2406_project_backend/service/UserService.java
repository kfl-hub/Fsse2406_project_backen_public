package com.fsse2406.fsse2406_project_backend.service;


import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;

public interface UserService {


    UserEntity getEntityByFirebaseUserData (FirebaseUserData firebaseUserData);
}
