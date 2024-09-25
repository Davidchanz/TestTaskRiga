package com.gn.testtaskriga.controller;

import com.gn.testtaskriga.dto.auth.LoginDto;
import com.gn.testtaskriga.dto.auth.TokenDto;
import com.gn.testtaskriga.dto.user.UserDto;
import com.gn.testtaskriga.exception.auth.AccessDeniedException;
import com.gn.testtaskriga.exception.auth.UserLoginException;
import com.gn.testtaskriga.mapper.user.UserMapper;
import com.gn.testtaskriga.model.user.CustomUserDetails;
import com.gn.testtaskriga.model.user.User;
import com.gn.testtaskriga.service.auth.AuthService;
import com.gn.testtaskriga.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/registration")
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto){
        this.userService.register(this.userMapper.userDtoToUser(userDto));
        return ResponseEntity.ok("User was registered");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginRequest){
        Authentication authentication = authService.authenticateUser(loginRequest)
                .orElseThrow(() -> new UserLoginException("Couldn't login user [" + loginRequest + "]"));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = authService.generateToken(customUserDetails);
        TokenDto token = new TokenDto();
        token.setToken(jwtToken);

        return ResponseEntity.ok(token);
    }
}
