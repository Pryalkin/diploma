package com.pryalkin.portal.controller;

import com.pryalkin.portal.exception.ExceptionHandling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends ExceptionHandling {

    @GetMapping("/home")
    public String home(){
        return "Hello";
    }
}
