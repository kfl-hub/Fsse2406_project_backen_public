package com.fsse2406.fsse2406_project_backend.objects.user.domainObject;

import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class UserResponseData {
    private Integer uid;
    private String firebaseUid;
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public UserResponseData(UserEntity user) {
        this.firebaseUid = user.getFirebaseUid();
        this.email = user.getEmail();
        this.uid = user.getUid();


    }}