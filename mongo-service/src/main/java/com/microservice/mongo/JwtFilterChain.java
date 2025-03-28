package com.microservice.mongo;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilterChain extends OncePerRequestFilter {

    @Autowired()
    private JwtService jwtService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer")) {
            filterChain.doFilter(request,   response);
            return;
        }

        token = token.substring(7);
        Claims claims = jwtService.validateToken(token);

        if (claims == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        logger.debug("Token");

        logger.debug("Account name", claims.get(0));
        logger.debug("Email", claims.get(2));
        logger.debug("Role Id", claims.get(1));

        String roleId = claims.get("roleId", String.class);
        request.setAttribute("roleId", roleId);

        filterChain.doFilter(request, response);
    }
}
