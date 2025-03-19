package com.microservice.authService.services;

import com.microservice.authService.dto.*;
import com.microservice.authService.util.JwtUtil;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang.StringUtils;
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

        UserVO userVO = restTemplate.postForObject("http://user-service/users/create", authRequest, UserVO.class);
        Assert.notNull(userVO, "Failed to register user. Please try again later");

        String accessToken = jwtUtil.generate(userVO, "ACCESS");
        String refreshToken = jwtUtil.generate(userVO, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse loginSystem(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();

        //validation
        if (StringUtils.isEmpty(loginRequest.getAccountName()) ||
                StringUtils.isEmpty((loginRequest.getPassword()))) {
            loginResponse.setSuccess(false);
            loginResponse.setMessage("User name (password) must be required");
            return loginResponse;
        }

        loginResponse = restTemplate.postForObject("http://user-service/users/loginSystem", loginRequest, LoginResponse.class);

        if (loginResponse.isSuccess()) {
            String accessToken = jwtUtil.generate(loginResponse.getData(), "ACCESS");
            String refreshToken = jwtUtil.generate(loginResponse.getData(), "REFRESH");

            AuthResponse authResponse = new AuthResponse(accessToken, refreshToken);

            loginResponse.setAuthResponse(authResponse);
        }

        return loginResponse;
    }
}
