/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:11:21 AM
 */

package com.gn.testtaskriga.service.article.impl;

import com.gn.testtaskriga.model.Article;
import com.gn.testtaskriga.repository.article.ArticleRepository;
import com.gn.testtaskriga.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceDBImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> listArticles() {
        return articleRepository.findAll();
    }
}
