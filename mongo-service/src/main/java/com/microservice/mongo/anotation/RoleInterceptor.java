package com.microservice.mongo.anotation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Aspect
@Component
public class RoleInterceptor {

    private final String SECRET_KEY = "5v8VrmHq93E/5Dg9lT10pQWmXz2yYkJr1s3H6z8aG0k=";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Around("@annotation(userRole)")
    public Object checkRole(ProceedingJoinPoint proceedingJoinPoint,
                            UserRole userRole) throws Throwable {
        String token = httpServletRequest.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is invalide");
        }

        token = token.substring(7);

        try {

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String roleId = (String) claims.get("roleId");

            if (!Arrays.stream(userRole.value()).anyMatch(role -> role.equals(roleId))) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access is denied");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is invalide");
        }

        return proceedingJoinPoint.proceed();
    }
}
