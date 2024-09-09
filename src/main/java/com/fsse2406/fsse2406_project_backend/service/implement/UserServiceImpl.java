package com.fsse2406.fsse2406_project_backend.service.implement;

import com.fsse2406.fsse2406_project_backend.exception.UserServiceException;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.UserResponseData;
import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import com.fsse2406.fsse2406_project_backend.repository.UserRepository;
import com.fsse2406.fsse2406_project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData) {
        return userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid()).
                orElseGet(() -> userRepository.save(new UserEntity(firebaseUserData)));
    }

    public UserResponseData getUserByFirebaseData(FirebaseUserData firebaseUserData) {
        try {
            return new UserResponseData(userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid()).
                    orElseThrow(() -> new UserServiceException(
                            "Get UerResponse Data-Not found-fid: "+firebaseUserData.getFirebaseUid())));
        } catch (UserServiceException exception) {
            logger.warn(exception.getMessage());
            throw exception;
        }
    }
}
