package com.gn.testtaskriga.service.user.impl;

import com.gn.testtaskriga.exception.exist.UserAlreadyExistException;
import com.gn.testtaskriga.model.User;
import com.gn.testtaskriga.repository.user.UserRepository;
import com.gn.testtaskriga.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserServiceDBImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    private String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    @Transactional
    public void register(User user) throws UserAlreadyExistException {
        if (emailExists(user.getLogin())) {
            throw new UserAlreadyExistException("User with login: '"
                    + user.getLogin() + "' is already exist!");
        }

        user.setPassword(encryptPassword(user.getPassword()));
        userRepository.save(user);
    }

    private boolean emailExists(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    @Override
    public User find(String login){
        return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Could not found a user with given login!"));
    }

}
