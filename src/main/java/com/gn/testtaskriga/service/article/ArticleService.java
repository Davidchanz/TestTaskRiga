package com.gn.testtaskriga.service.article;

import com.gn.testtaskriga.dto.article.ArticleCount;
import com.gn.testtaskriga.model.article.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    List<Article> listArticles(Pageable pageable);

    void createArticle(Article article);

    List<ArticleCount> listArticlesDailyCountByLastWeek();
}
