package com.fsse2406.fsse2406_project_backend.util;

import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUserData (JwtAuthenticationToken jwtToken){
        return  new FirebaseUserData(jwtToken);
    }
}
