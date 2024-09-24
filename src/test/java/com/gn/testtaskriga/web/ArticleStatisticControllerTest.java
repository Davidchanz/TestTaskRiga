/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:6:17 PM
 */

package com.gn.testtaskriga.web;

import com.gn.testtaskriga.config.SecurityConfig;
import com.gn.testtaskriga.controller.ArticleController;
import com.gn.testtaskriga.controller.ArticleStatisticController;
import com.gn.testtaskriga.mapper.article.ArticleMapper;
import com.gn.testtaskriga.service.article.ArticleService;
import com.gn.testtaskriga.service.pagination.PaginationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ArticleStatisticController.class})
@MockBean(ArticleService.class)
public class ArticleStatisticControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser(value = "bob", authorities = "ADMIN")
    public void whenCallingListArticlesAuthorizedReturn_Ok() throws Exception {
        mvc.perform(get("/api/v1/admin/statistic/article/dailyCountByWeek"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void whenCallingListArticlesNotAuthorizedReturn_401() throws Exception {
        mvc.perform(get("/api/v1/admin/statistic/article/dailyCountByWeek"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}

