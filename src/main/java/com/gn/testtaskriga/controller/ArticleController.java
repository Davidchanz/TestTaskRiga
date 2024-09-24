/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:11:17 AM
 */

package com.gn.testtaskriga.controller;

import com.gn.testtaskriga.dto.article.ArticleDto;
import com.gn.testtaskriga.mapper.article.ArticleMapper;
import com.gn.testtaskriga.model.article.Article;
import com.gn.testtaskriga.service.article.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")

public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;
    @GetMapping
    public ResponseEntity<List<ArticleDto>> listArticles(){
        List<Article> articleList = articleService.listArticles();
        return ResponseEntity.ok(articleList.stream().map(articleMapper::articleToArticleDto).toList());
    }

    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody @Valid ArticleDto articleDto){
        Article article = articleMapper.articleDtoToArticle(articleDto);
        articleService.createArticle(article);
        return ResponseEntity.ok("Article was created");
    }
}
