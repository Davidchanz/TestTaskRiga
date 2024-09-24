/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:11:17 AM
 */

package com.gn.testtaskriga.controller;

import com.gn.testtaskriga.dto.article.ArticleDto;
import com.gn.testtaskriga.mapper.ArticleMapper;
import com.gn.testtaskriga.model.Article;
import com.gn.testtaskriga.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
