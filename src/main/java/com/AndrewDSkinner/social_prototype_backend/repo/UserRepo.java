package com.AndrewDSkinner.social_prototype_backend.repo;

import com.AndrewDSkinner.social_prototype_backend.User;

import java.util.Optional;

public interface UserRepo {
    Optional<User> saveUser(User user);
}
