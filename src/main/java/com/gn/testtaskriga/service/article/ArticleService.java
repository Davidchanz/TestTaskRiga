package com.gn.testtaskriga.service.article;

import com.gn.testtaskriga.model.article.Article;

import java.util.List;

public interface ArticleService {
    List<Article> listArticles();

    void createArticle(Article article);
}
