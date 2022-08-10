package com.pryalkin.portal.controller;

import com.pryalkin.portal.entity.User;
import com.pryalkin.portal.exception.ExceptionHandling;
import com.pryalkin.portal.exception.model.*;
import com.pryalkin.portal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController extends ExceptionHandling {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, UsernameExistException, EmailExistException, UsernameIsInvalidException, EmailIsInvalidException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }
}
