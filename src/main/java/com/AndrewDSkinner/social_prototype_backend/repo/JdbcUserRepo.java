package com.AndrewDSkinner.social_prototype_backend.repo;

import com.AndrewDSkinner.social_prototype_backend.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcUserRepo implements UserRepo {
    private  final JdbcTemplate jdbcTemplate;

    public JdbcUserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> saveUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        if (rowsAffected > 0) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
