package com.AndrewDSkinner.social_prototype_backend.controller;

import com.AndrewDSkinner.social_prototype_backend.config.JwtUtil;
import com.AndrewDSkinner.social_prototype_backend.dto.JwtResponse;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTORequest;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTOResponse;
import com.AndrewDSkinner.social_prototype_backend.exceptions.ErrorResponse;
import com.AndrewDSkinner.social_prototype_backend.exceptions.UserRegistrationException;
import com.AndrewDSkinner.social_prototype_backend.service.UserService;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserDTOResponse> login(@RequestBody UserDTORequest userDTORequest) {
//        // TODO: Implement authentication logic
//        // For now, returning a mock response
//        UserDTOResponse response = new UserDTOResponse(
//            "mock-jwt-token",
//            userDTORequest.getFirstName(),
//            userDTORequest.getLastName(),
//            userDTORequest.getEmail()
//        );
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTORequest userDTORequest) throws UserRegistrationException {
        try {
            UserDTOResponse userDTOResponse = userService.registerUser(userDTORequest);

            String token;
            JwtResponse response = null;
            if (userDTOResponse != null) {
                token = jwtUtil.generateToken(userDTOResponse);
                response = new JwtResponse(token, userDTOResponse);
                return ResponseEntity.ok(response);
            } else {
                logger.error("User registration failed - service returned null response.");
                ErrorResponse errorResponse = new ErrorResponse("User registration failed.", 400);
                return ResponseEntity.badRequest().body(errorResponse);
            }
        } catch (UserRegistrationException e) {
            logger.error("User registration failed: {}", e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 409);
            return ResponseEntity.status(409).body(errorResponse);
        } catch (Exception e) {
            logger.error("Unexpected error during registration", e);
            ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred", 500);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
