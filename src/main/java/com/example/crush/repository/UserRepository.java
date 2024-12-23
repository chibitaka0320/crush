package com.example.crush.repository;

import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.crush.domain.User;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate template;

    public UserRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return user;
    };

    public User load(Integer id) {
        String sql = "SELECT id, name, password, email, created_at, updated_at FROM users WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
        return user;
    }

    public void update(User user) {
        String sql = "UPDATE users SET id = :id, name = :name, password = :password, email = :email, updated_at = :updatedAt WHERE id = :id";
        user.setUpdatedAt(LocalDateTime.now());
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        template.update(sql, param);
    }

    public Integer insert(User user) {
        String sql = "INSERT INTO users(name, password, email, created_at, updated_at) VALUES(:name, :password, :email, :createdAt, :updatedAt)";
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String[] keyColumnNames = {"id"};
        template.update(sql, param, keyHolder, keyColumnNames);

        return keyHolder.getKey().intValue();
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM users WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }
}
