package com.gn.testtaskriga;

import com.gn.testtaskriga.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class TestTaskRigaApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SpringApplication.run(TestTaskRigaApplication.class, args);
    }

}
