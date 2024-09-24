/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:3:53 PM
 */

package com.gn.testtaskriga.controller;

import com.gn.testtaskriga.dto.article.ArticleCount;
import com.gn.testtaskriga.dto.article.ArticleDto;
import com.gn.testtaskriga.model.article.Article;
import com.gn.testtaskriga.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/statistic/article")
public class ArticleStatisticController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/dailyCountByWeek")
    public ResponseEntity<List<ArticleCount>> articlesDailyCountByLastWeek(){
        List<ArticleCount> articleCounts = articleService.listArticlesDailyCountByLastWeek();
        return ResponseEntity.ok(articleCounts);
    }

}
