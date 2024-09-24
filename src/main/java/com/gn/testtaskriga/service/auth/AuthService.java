package com.gn.testtaskriga.service.auth;

import com.gn.testtaskriga.dto.auth.LoginDto;
import com.gn.testtaskriga.model.user.CustomUserDetails;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthService {
    Optional<Authentication> authenticateUser(LoginDto loginRequest);

    String generateToken(CustomUserDetails customUserDetails);
}
