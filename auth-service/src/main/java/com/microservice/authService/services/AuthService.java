package com.microservice.authService.services;

import com.microservice.authService.dto.AuthRequest;
import com.microservice.authService.dto.AuthResponse;
import com.microservice.authService.dto.UserVO;
import com.microservice.authService.util.JwtUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService implements IAuthService {

    private final RestTemplate restTemplate;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(RestTemplate restTemplate, JwtUtil jwtUtil) {
        this.restTemplate = restTemplate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse register(AuthRequest authRequest) {
        //do validation if user already exists
        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));

        UserVO userVO = restTemplate.postForObject("http://localhost:11000/users/create", authRequest, UserVO.class);
        Assert.notNull(userVO, "Failed to register user. Please try again later");

        String accessToken = jwtUtil.generate(userVO, "ACCESS");
        String refreshToken = jwtUtil.generate(userVO, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
