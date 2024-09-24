/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:5:13 PM
 */

package com.gn.testtaskriga.service.pagination;

import com.gn.testtaskriga.service.pagination.PaginationService;
import com.gn.testtaskriga.service.pagination.impl.PaginationServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class PaginationConfig {

    @Bean
    public PaginationService paginationService(){
        return new PaginationServiceImpl();
    }
}
