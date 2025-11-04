package com.AndrewDSkinner.social_prototype_backend.service;

import com.AndrewDSkinner.social_prototype_backend.User;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTORequest;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTOResponse;
import com.AndrewDSkinner.social_prototype_backend.exceptions.UserRegistrationException;
import com.AndrewDSkinner.social_prototype_backend.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class BasicUserService implements UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public BasicUserService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTOResponse registerUser(UserDTORequest userDto) throws UserRegistrationException {
        User user = new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword() != null && !userDto.getPassword().isEmpty() ?
                        passwordEncoder.encode(userDto.getPassword()) : null
        );

        User savedUser = userRepo.saveUser(user).orElseThrow(() -> new UserRegistrationException("User registration failed"));

        return new UserDTOResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );
    }
}
