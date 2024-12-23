package com.example.crush.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crush.domain.Comment;
import com.example.crush.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findByArticleId(Integer articleId) {
        return commentRepository.findByArticleId(articleId);
    }
}
