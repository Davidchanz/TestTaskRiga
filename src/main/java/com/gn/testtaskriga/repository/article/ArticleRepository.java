package com.gn.testtaskriga.repository.article;

import com.gn.testtaskriga.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
