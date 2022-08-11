package com.pryalkin.portal.service;

import com.pryalkin.portal.entity.User;
import com.pryalkin.portal.exception.model.*;
import org.springframework.messaging.MessagingException;

import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, UsernameIsInvalidException, EmailIsInvalidException;
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    List<User> getUsers();
}
