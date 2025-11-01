package com.AndrewDSkinner.social_prototype_backend.service;

import com.AndrewDSkinner.social_prototype_backend.User;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTORequest;

public interface UserService {
    User registerUser(UserDTORequest user);
}
