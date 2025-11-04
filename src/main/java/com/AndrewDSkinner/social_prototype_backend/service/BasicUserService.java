package com.AndrewDSkinner.social_prototype_backend.service;

import com.AndrewDSkinner.social_prototype_backend.User;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTORequest;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTOResponse;
import com.AndrewDSkinner.social_prototype_backend.exceptions.UserRegistrationException;
import com.AndrewDSkinner.social_prototype_backend.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
public class BasicUserService implements UserService {

    private final UserRepo userRepo;

    public BasicUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDTOResponse registerUser(UserDTORequest userDto) throws UserRegistrationException {
        User user = new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword() != null && !userDto.getPassword().isEmpty() ?
                        BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()) : null
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
