package com.AndrewDSkinner.social_prototype_backend.service;

import com.AndrewDSkinner.social_prototype_backend.User;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTORequest;
import com.AndrewDSkinner.social_prototype_backend.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasicUserService implements UserService {

    private final UserRepo userRepo;

    public BasicUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registerUser(UserDTORequest userDto) {
        User user = new User(
            userDto.getFirstName(),
            userDto.getLastName(),
            userDto.getEmail(),
            userDto.getPassword()
        );

        Optional<User> result = userRepo.saveUser(user);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("User registration failed");
        }
    }
}
