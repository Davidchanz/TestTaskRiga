package com.gn.testtaskriga.mapper;

import com.gn.testtaskriga.dto.article.ArticleDto;
import com.gn.testtaskriga.model.Article;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "author.login", target = "author")
    ArticleDto articleToArticleDto(Article model);
}
