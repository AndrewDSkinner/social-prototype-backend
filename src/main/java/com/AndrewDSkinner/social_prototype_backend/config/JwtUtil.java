package com.AndrewDSkinner.social_prototype_backend.config;


import com.AndrewDSkinner.social_prototype_backend.dto.UserDTOResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDTOResponse userDTOResponse) {
        // 30 minutes
        long expiration = 1000 * 60 * 30;
        return Jwts.builder()
                .setSubject(userDTOResponse.getEmail())
                .claim("firstName", userDTOResponse.getFirstName())
                .claim("lastName", userDTOResponse.getLastName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }
}
