package com.gn.testtaskriga.service.auth;

import com.gn.testtaskriga.dto.auth.LoginDto;
import com.gn.testtaskriga.model.user.CustomUserDetails;
import com.gn.testtaskriga.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public Optional<Authentication> authenticateUser(LoginDto loginRequest) {
        try{
            return Optional.ofNullable(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin(),
                    loginRequest.getPassword())));
        }catch (AuthenticationException ex){
            return Optional.empty();
        }
    }

    @Override
    public String generateToken(CustomUserDetails customUserDetails) {
        return tokenProvider.generateToken(customUserDetails);
    }

}
