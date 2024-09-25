/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/25/24
 * Time:12:52 PM
 */

package com.gn.testtaskriga.jpa;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.crypto.RsaKeyConversionServicePostProcessor;

@TestConfiguration
public class JpaConfiguration {

    @Bean
    public static BeanFactoryPostProcessor conversionServicePostProcessor() {
        return new RsaKeyConversionServicePostProcessor();
    }

}
