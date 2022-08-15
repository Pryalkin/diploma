package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
}
