/**
 * Author: Giorgi Nodia
 * User:davidchanzz
 * Date:9/24/24
 * Time:5:02 PM
 */

package com.gn.testtaskriga.jpa;

import com.gn.testtaskriga.config.RsaKeyProperties;
import com.gn.testtaskriga.model.user.User;
import com.gn.testtaskriga.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("TEST")
public class UserRepositoryJPATest {

    @Autowired
    UserRepository userRepository;

    @Test
    void whenQueryUserByLoginReturn_OK() {
        User admin = userRepository.findByLogin("admin").get();
        Assertions.assertNotNull(admin);
        Assertions.assertEquals(admin.getLogin(), "admin");
    }

}
