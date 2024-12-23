package com.example.crush.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.crush.domain.Comment;

@Repository
public class CommentRepository {

    private final NamedParameterJdbcTemplate template;

    public CommentRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setUserId(rs.getInt("user_id"));
        comment.setArticleId(rs.getInt("article_id"));
        comment.setContent(rs.getString("content"));
        comment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        comment.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

        return comment;
    };

    public List<Comment> findByArticleId(Integer articleId) {
        String sql = "SELECT id, user_id, article_id, content, created_at, updated_at FROM comments WHERE article_id = :articleId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
        return commentList;
    }
}
