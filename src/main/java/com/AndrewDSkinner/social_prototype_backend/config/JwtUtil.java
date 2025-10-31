package com.AndrewDSkinner.social_prototype_backend.config;


import com.AndrewDSkinner.social_prototype_backend.dto.UserDTORequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDTORequest userDTORequest) {
        // 30 minutes
        long expiration = 1000 * 60 * 30;
        return Jwts.builder()
                .setSubject(userDTORequest.getEmail())
                .claim("firstName", userDTORequest.getFirstName())
                .claim("lastName", userDTORequest.getLastName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }
}
