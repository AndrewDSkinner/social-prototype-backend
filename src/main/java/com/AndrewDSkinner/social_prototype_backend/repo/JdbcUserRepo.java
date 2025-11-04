package com.AndrewDSkinner.social_prototype_backend.repo;

import com.AndrewDSkinner.social_prototype_backend.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcUserRepo implements UserRepo {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
        public Optional<User> saveUser(User user) {
            String insertSql = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
            org.springframework.jdbc.support.KeyHolder keyHolder = new org.springframework.jdbc.support.GeneratedKeyHolder();
            int rowsAffected = jdbcTemplate.update(connection -> {
                java.sql.PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{"id"});
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                return ps;
            }, keyHolder);
            if (rowsAffected <= 0) {
                return Optional.empty();
            }
            Number key = keyHolder.getKeyAs(Number.class);
            if (key == null) {
                return Optional.empty();
            }
            Long id = key.longValue();
            String selectSql = "SELECT id, first_name, last_name, email FROM users WHERE id = ?";
            User saved = jdbcTemplate.queryForObject(selectSql, (rs, rowNum) -> {
                User u = new User(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                null);
                return u;
            }, id);
            return Optional.ofNullable(saved);
        }
}
