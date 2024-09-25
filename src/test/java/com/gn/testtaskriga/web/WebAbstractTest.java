/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/25/24
 * Time:1:21 PM
 */

package com.gn.testtaskriga.web;

import com.gn.testtaskriga.dto.auth.TokenDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("TEST")
public abstract class WebAbstractTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    protected HttpHeaders getTokenHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = """
                        {
                            "login": "jackblack",
                            "password": "admin"
                        }
                        """;

        HttpEntity<String> tokenRequestEntity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<TokenDto> response = restTemplate
                .postForEntity("/api/v1/auth/login", tokenRequestEntity, TokenDto.class);

        headers.add("Authorization", "Bearer " + response.getBody().getToken());
        return headers;
    }

    protected HttpHeaders getAdminTokenHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = """
                        {
                            "login": "admin",
                            "password": "admin"
                        }
                        """;

        HttpEntity<String> tokenRequestEntity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<TokenDto> response = restTemplate
                .postForEntity("/api/v1/auth/login", tokenRequestEntity, TokenDto.class);

        headers.add("Authorization", "Bearer " + response.getBody().getToken());
        return headers;
    }
}
