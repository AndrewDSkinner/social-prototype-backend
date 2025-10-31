package com.AndrewDSkinner.social_prototype_backend.controller;

import com.AndrewDSkinner.social_prototype_backend.config.JwtUtil;
import com.AndrewDSkinner.social_prototype_backend.dto.JwtResponse;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTORequest;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTOResponse> login(@RequestBody UserDTORequest userDTORequest) {
        // TODO: Implement authentication logic
        // For now, returning a mock response
        UserDTOResponse response = new UserDTOResponse(
            "mock-jwt-token",
            userDTORequest.getFirstName(),
            userDTORequest.getLastName(),
            userDTORequest.getEmail()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody UserDTORequest userDTORequest) {
        String token = jwtUtil.generateToken(userDTORequest);
        JwtResponse response = new JwtResponse(token);

        return ResponseEntity.ok(response);
    }

}
