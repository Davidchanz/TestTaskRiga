/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:6:17 PM
 */

package com.gn.testtaskriga.web;

import com.gn.testtaskriga.config.SecurityConfig;
import com.gn.testtaskriga.controller.ArticleController;
import com.gn.testtaskriga.mapper.article.ArticleMapper;
import com.gn.testtaskriga.model.user.CustomUserDetails;
import com.gn.testtaskriga.model.user.User;
import com.gn.testtaskriga.service.article.ArticleService;
import com.gn.testtaskriga.service.pagination.PaginationService;
import com.gn.testtaskriga.service.user.impl.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ArticleController.class})
@MockBean(ArticleMapper.class)
@MockBean(ArticleService.class)
@MockBean(PaginationService.class)
public class ArticleControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser("bob")
    public void whenCallingListArticlesAuthorizedReturn_Ok() throws Exception {
        mvc.perform(get("/api/v1/article"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void whenCallingListArticlesNotAuthorizedReturn_401() throws Exception {
        mvc.perform(get("/api/v1/article"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}

