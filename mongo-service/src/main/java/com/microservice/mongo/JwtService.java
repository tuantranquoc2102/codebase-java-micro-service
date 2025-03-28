package com.microservice.mongo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtService {
    private final String secret = "5v8VrmHq93E/5Dg9lT10pQWmXz2yYkJr1s3H6z8aG0k=";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public Claims validateToken(String token) {
        try {
            Claims claims =  Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            logger.debug("Token access ");
            logger.debug(claims.values().toString());
            return claims;
        } catch (Exception e) {
            return null;
        }
    }
}
