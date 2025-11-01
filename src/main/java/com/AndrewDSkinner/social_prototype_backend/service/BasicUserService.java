package com.AndrewDSkinner.social_prototype_backend.service;

import com.AndrewDSkinner.social_prototype_backend.User;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTORequest;
import org.springframework.stereotype.Service;

@Service
public class BasicUserService implements UserService {

    @Override
    public User registerUser(UserDTORequest userDto) {
        User user = new User(
            userDto.getFirstName(),
            userDto.getLastName(),
            userDto.getEmail(),
            userDto.getPassword()
        );

        return user;
    }
}
