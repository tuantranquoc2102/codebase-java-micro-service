package com.microservice.authService.services;

import com.microservice.authService.dto.*;
import com.microservice.authService.exceptions.BusinessException;
import com.microservice.authService.util.JwtUtil;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.lang.reflect.ParameterizedType;

@Service
public class AuthService implements IAuthService {

    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(RestTemplate restTemplate, JwtUtil jwtUtil, WebClient.Builder wBuilder) {
        this.restTemplate = restTemplate;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public ResponseDto<AuthResponse> register(AuthRequest authRequest) {
        String msg;
        try {
            //do validation if user already exists
            //authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));

            ResponseEntity<ResponseDto<UserVO>> response = restTemplate.exchange(
                    "http://user-service/users/create",
                    HttpMethod.POST,
                    new HttpEntity<>(authRequest),
                    new ParameterizedTypeReference<ResponseDto<UserVO>>() {}
            );


            ResponseDto<UserVO> userVO = response.getBody();

            if (userVO == null) {
                return new ResponseDto<AuthResponse>(false, "Failed to save data", null);
            }

            Assert.notNull(userVO, "Failed to register user. Please try again later");

            if (!userVO.isSuccess()) {
                return new ResponseDto<AuthResponse>(false, userVO.getMessage(), null);
            }

            String accessToken = jwtUtil.generate(userVO.getData(), "ACCESS");
            String refreshToken = jwtUtil.generate(userVO.getData(), "REFRESH");

            return new ResponseDto<AuthResponse>(true, "", new AuthResponse(accessToken, refreshToken
                    , userVO.getData().getRoleId()));

        } catch (HttpClientErrorException e) {
            msg = "User Service Error : " + e.getResponseBodyAsString();
        } catch (Exception e) {
            msg = "An unexpected error occurred:" + e.getMessage();
        }
        return  new ResponseDto<AuthResponse>(false, msg, null);
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

        //String password = BCrypt.hashpw(loginRequest.getPassword(), BCrypt.gensalt());

        // ma hoa password
        //loginRequest.setPassword(password);

        loginResponse = restTemplate.postForObject("http://user-service/users/loginSystem", loginRequest, LoginResponse.class);

        if (loginResponse.isSuccess()) {
            String accessToken = jwtUtil.generate(loginResponse.getData(), "ACCESS");
            String refreshToken = jwtUtil.generate(loginResponse.getData(), "REFRESH");

            AuthResponse authResponse = new AuthResponse(accessToken, refreshToken, loginResponse.getData().getRoleId());

            loginResponse.setAuthResponse(authResponse);
        }

        return loginResponse;
    }
}
