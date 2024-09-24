/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:11:21 AM
 */

package com.gn.testtaskriga.service.article.impl;

import com.gn.testtaskriga.dto.article.ArticleCount;
import com.gn.testtaskriga.model.article.Article;
import com.gn.testtaskriga.repository.article.ArticleRepository;
import com.gn.testtaskriga.service.article.ArticleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceDBImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Transactional
    public List<Article> listArticles(Pageable pageable) {
        return articleRepository.findAll(pageable).getContent();
    }

    @Override
    @Transactional
    public void createArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<ArticleCount> listArticlesDailyCountByLastWeek() {
        return articleRepository.findDailyCountByLastWeek();
    }
}
