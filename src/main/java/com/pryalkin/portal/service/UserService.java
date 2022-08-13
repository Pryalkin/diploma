package com.pryalkin.portal.service;

import com.pryalkin.portal.entity.user.User;
import com.pryalkin.portal.exception.model.*;
import org.springframework.messaging.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, UsernameIsInvalidException, EmailIsInvalidException, jakarta.mail.MessagingException;
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    List<User> getUsers();
    User editUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail) throws UserNotFoundException, EmailIsInvalidException, EmailExistException, UsernameIsInvalidException, UsernameExistException;
    void deleteUser(long id);
    void resetPassword(String email) throws jakarta.mail.MessagingException, EmailNotFoundException;
    User updateProfileImage(String username, MultipartFile profileImage) throws EmailIsInvalidException, UsernameIsInvalidException, UserNotFoundException, EmailExistException, UsernameExistException, IOException;
}
