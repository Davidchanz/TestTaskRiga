/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:5:45 PM
 */

package com.gn.testtaskriga.service.statistic;

import com.gn.testtaskriga.service.article.ArticleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("TEST")
public class StatisticTest {

    @Autowired
    ArticleService articleService;

    @Test
    public void whenArticleGetStatisticsDailyByWeek_Ok(){
        Assertions.assertEquals(6, articleService.listArticlesDailyCountByLastWeek().size());
        Assertions.assertEquals(1, articleService.listArticlesDailyCountByLastWeek().get(0).getArticleCount());
        Assertions.assertEquals(3, articleService.listArticlesDailyCountByLastWeek().get(2).getArticleCount());
    }
}
