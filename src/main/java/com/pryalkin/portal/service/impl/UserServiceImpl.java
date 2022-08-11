package com.pryalkin.portal.service.impl;

import com.pryalkin.portal.entity.User;
import com.pryalkin.portal.entity.UserPrincipal;
import com.pryalkin.portal.exception.model.*;
import com.pryalkin.portal.repository.UserRepository;
import com.pryalkin.portal.service.LoginAttemptService;
import com.pryalkin.portal.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessagingException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pryalkin.portal.constant.FileConstant.*;
import static com.pryalkin.portal.constant.UserImplConstant.*;
import static com.pryalkin.portal.enumeration.Role.ROLE_USER;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
@Transactional
@Qualifier("userDetailsService")
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            validateLoginAttempt(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            log.info(FOUND_USER_BY_USERNAME + username);
            return userPrincipal;
        }
    }

    @Override
    public User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException, UsernameIsInvalidException, EmailIsInvalidException {
        validateNewUsernameAndEmail(EMPTY, username, email);
        User user = new User();
        user.setUserId(generateUserId());
        String password = generatePassword();
        String encodedPassword = encodePassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setJoinDate(new Date());
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(ROLE_USER.name());
        user.setAuthorities(ROLE_USER.getAuthorities());
        user.setProfileImageUrl(getTemporaryProfileImageUrl(username));
        userRepository.save(user);
        log.info("New user password: " + password);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException, UsernameIsInvalidException, EmailIsInvalidException {
        Pattern patternForUsername = Pattern.compile("[A-Za-z][A-Za-z0-9_$]+");
        Matcher matcherForUsername = patternForUsername.matcher(newUsername);
        boolean validateNewUsername = matcherForUsername.find();
        if (!validateNewUsername){
            throw new UsernameIsInvalidException(NOT_A_VALID_USERNAME + newUsername);
        }
        Pattern patternForEmail = Pattern.compile("\\b[A-Za-z0-9_$.%+-]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,4}\\b");
        Matcher matcherForEmail = patternForEmail.matcher(newEmail);
        boolean validateNewEmail = matcherForEmail.find();
        if (!validateNewEmail){
            throw new EmailIsInvalidException(NOT_A_VALID_EMAIL + newEmail);
        }
        User userByNewUsername = findUserByUsername(newUsername);
        User userByNewEmail = findUserByEmail(newEmail);
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = findUserByUsername(currentUsername);
            if(currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentUser;
        } else {
            if(userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private String getTemporaryProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private void validateLoginAttempt(User user) {
        if(user.isNotLocked()){
            if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())){
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
