/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:6:17 PM
 */

package com.gn.testtaskriga.web;

import com.gn.testtaskriga.dto.article.ArticleDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

public class ArticleControllerTest extends WebAbstractTest{

    @Test
    public void whenCallingListArticlesAuthorizedReturn_ArticleList() {
        HttpHeaders headers = getTokenHeader();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<ArticleDto>> response = restTemplate.exchange(
                "/api/v1/article", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ArticleDto>>() {
                });

        Assertions.assertEquals(8, response.getBody().size());
    }

    @Test
    public void whenCallingListArticlesNotAuthorizedReturn_401() {
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<ArticleDto>> response = restTemplate.exchange(
                "/api/v1/article", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ArticleDto>>() {
                });

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    @DirtiesContext
    public void whenCallingCreateArticleAuthorizedReturn_Ok() {
        HttpHeaders headers = getTokenHeader();

        String articleDto = """
                {
                    "title": "title",
                    "author": "jackblack",
                    "content":  "буль-буль-буль",
                    "publishedDate": "2024-09-20T00:00:00.000Z"
                }
                """;

        HttpEntity<String> entity = new HttpEntity<>(articleDto, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/v1/article", HttpMethod.POST, entity, String.class);

        Assertions.assertEquals("Article was created", response.getBody());
    }

    @Test
    public void whenCallingCreateArticleNotAuthorizedReturn_401() {
        String articleDto = """
                {
                    "title": "title",
                    "author": "jackblack",
                    "content":  "буль-буль-буль",
                    "publishedDate": "2024-09-20T00:00:00.000Z"
                }
                """;

        HttpEntity<String> entity = new HttpEntity<>(articleDto, new HttpHeaders());
        ResponseEntity<List<ArticleDto>> response = restTemplate.exchange(
                "/api/v1/article", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ArticleDto>>() {
                });

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}

