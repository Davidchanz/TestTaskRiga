/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:5:10 PM
 */

package com.gn.testtaskriga.service.pagination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        PaginationConfig.class
})
@TestPropertySource(locations={"classpath:application-TEST.properties"})
@ActiveProfiles("TEST")
public class PaginationServiceTest {

    @Autowired
    PaginationService paginationService;

    @Test
    public void whenGettingPage_OK(){
        Assertions.assertEquals(0, paginationService.getPage(null).getPageNumber());
        Assertions.assertEquals(0, paginationService.getPage("0").getPageNumber());
        Assertions.assertEquals(0, paginationService.getPage("1").getPageNumber());
        Assertions.assertEquals(4, paginationService.getPage("5").getPageNumber());
    }

}
