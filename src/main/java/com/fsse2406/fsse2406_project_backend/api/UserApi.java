package com.fsse2406.fsse2406_project_backend.api;

import com.fsse2406.fsse2406_project_backend.objects.user.dto.UserResponseDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {
    @GetMapping("/me/details")
    public UserResponseDto getMyUserDetails(JwtAuthenticationToken jwtToken) {
        UserResponseDto loginUser = new UserResponseDto(jwtToken);
        return loginUser;
    }
}