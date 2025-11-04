package com.AndrewDSkinner.social_prototype_backend.service;

import com.AndrewDSkinner.social_prototype_backend.dto.UserDTORequest;
import com.AndrewDSkinner.social_prototype_backend.dto.UserDTOResponse;
import com.AndrewDSkinner.social_prototype_backend.exceptions.UserRegistrationException;

public interface UserService {
    UserDTOResponse registerUser(UserDTORequest user) throws UserRegistrationException;
}
