package com.example.crush.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.crush.domain.Article;
import com.example.crush.domain.Comment;
import com.example.crush.service.ArticleService;
import com.example.crush.service.CommentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("")
    public List<Article> getArticles() {
        List<Article> articleList = articleService.findAll();
        if(articleList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return articleList;
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Integer id) {
        Article article = articleService.findById(id);
        if(article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return article;
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable Integer id) {
        List<Comment> commentList = commentService.findByArticleId(id);
        if(commentList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return commentList;
    }

    @PostMapping("")
    public ResponseEntity<Void> registerArticle(@Validated @RequestBody Article article) {
        Integer articleId = articleService.insert(article);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(articleId)
            .toUri();
        ResponseEntity<Void> result = ResponseEntity.created(location).build();

        return result;
    }
}
