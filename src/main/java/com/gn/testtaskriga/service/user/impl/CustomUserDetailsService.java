package com.gn.testtaskriga.service.user.impl;

import com.gn.testtaskriga.exception.auth.UserFoundException;
import com.gn.testtaskriga.model.user.CustomUserDetails;
import com.gn.testtaskriga.model.user.User;
import com.gn.testtaskriga.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) {
        User dbUser = userRepository.findByLogin(login).orElseThrow(() -> new UserFoundException("Couldn't find a matching user login for " + login));

        return new CustomUserDetails(dbUser);
    }
}
