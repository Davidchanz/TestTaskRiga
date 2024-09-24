package com.gn.testtaskriga.service.user;

import com.gn.testtaskriga.exception.exist.UserAlreadyExistException;
import com.gn.testtaskriga.model.user.User;

public interface UserService {
    void register(User user) throws UserAlreadyExistException;
    User find(String login);
}
