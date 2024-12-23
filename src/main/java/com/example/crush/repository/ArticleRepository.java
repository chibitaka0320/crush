package com.example.crush.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.crush.domain.Article;

@Repository
public class ArticleRepository {

    private final NamedParameterJdbcTemplate template;

    public ArticleRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setUserId(rs.getInt("user_id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        article.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return article;
    };

    public List<Article> findAll() {
        String sql = "SELECT id, user_id, title, content, created_at, updated_at FROM articles";
        List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
        return articleList;
    }

    public Article load(Integer id) {
        String sql = "SELECT id, user_id, title, content, created_at, updated_at FROM articles WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Article article = template.queryForObject(sql, param, ARTICLE_ROW_MAPPER);
        return article;
    }

    public Integer insert(Article article) {
        String sql = "INSERT INTO articles(user_id, title, content, created_at, updated_at) VALUES(:userId, :title, :content, :createdAt, :updatedAt)";
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());

        SqlParameterSource param = new BeanPropertySqlParameterSource(article);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String[] keyColumnNames = {"id"};
        template.update(sql, param, keyHolder, keyColumnNames);

        return keyHolder.getKey().intValue();
    }
}
