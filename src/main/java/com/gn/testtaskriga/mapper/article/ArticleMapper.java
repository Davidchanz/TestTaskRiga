package com.gn.testtaskriga.mapper.article;

import com.gn.testtaskriga.dto.article.ArticleDto;
import com.gn.testtaskriga.model.Article;
import com.gn.testtaskriga.service.user.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ArticleMapper {

    @Autowired
    protected UserService userService;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "author.login", target = "author")
    public abstract ArticleDto articleToArticleDto(Article model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "author", expression = "java(userService.find(author.login))")
    public abstract Article articleDtoToArticle(ArticleDto dto);
}
