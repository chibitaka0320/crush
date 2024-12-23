package com.example.crush.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crush.domain.Article;
import com.example.crush.repository.ArticleRepository;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Integer id) {
        return articleRepository.load(id);
    }

    public Integer insert(Article article) {
        return articleRepository.insert(article);
    }
}
