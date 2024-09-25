/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:6:17 PM
 */

package com.gn.testtaskriga.web;

import com.gn.testtaskriga.dto.article.ArticleCount;
import com.gn.testtaskriga.dto.article.ArticleDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

public class ArticleStatisticControllerTest extends WebAbstractTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenCallingListArticlesAuthorizedAdminReturn_ArticleCountList() {
        HttpHeaders headers = getAdminTokenHeader();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<ArticleCount>> response = restTemplate.exchange(
                "/api/v1/admin/statistic/article/dailyCountByWeek", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ArticleCount>>() {
                });

        HttpHeaders headers1 = getTokenHeader();

        HttpEntity<String> entity1 = new HttpEntity<>(headers1);
        ResponseEntity<List<ArticleDto>> response1 = restTemplate.exchange(
                "/api/v1/article", HttpMethod.GET, entity1, new ParameterizedTypeReference<List<ArticleDto>>() {
                });

        List<ArticleDto> body1 = response1.getBody();

        List<ArticleCount> body = response.getBody();

        Assertions.assertEquals(6, response.getBody().size());
    }

    @Test
    public void whenCallingListArticlesNotAuthorizedReturn_401() throws Exception {

        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<ArticleCount>> response = restTemplate.exchange(
                "/api/v1/admin/statistic/article/dailyCountByWeek", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ArticleCount>>() {
                });

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void whenCallingListArticlesAuthorizedNotAdminReturn_403() throws Exception {
        HttpHeaders headers = getTokenHeader();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<?> response = restTemplate.exchange(
                "/api/v1/admin/statistic/article/dailyCountByWeek", HttpMethod.GET, entity, Object.class);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}

