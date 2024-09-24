package com.gn.testtaskriga.repository.article;

import com.gn.testtaskriga.dto.article.ArticleCount;
import com.gn.testtaskriga.model.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT new com.gn.testtaskriga.dto.article.ArticleCount(CAST(a.publishedDate as LocalDate) , COUNT(a))" +
            " FROM Article a WHERE DAY(CURRENT_DATE()) - DAY(a.publishedDate) <= 7 AND" +
            " DAY(CURRENT_DATE()) - DAY(a.publishedDate) >= 0" +
            " GROUP BY CAST(a.publishedDate as DATE) ORDER BY CAST(a.publishedDate as DATE)")
    List<ArticleCount> findDailyCountByLastWeek();
}