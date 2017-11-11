package com.karel.video.rental.controller;


import com.karel.video.rental.domain.User;
import com.karel.video.rental.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/user")
@Slf4j
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/list")
    public @ResponseBody
    Iterable<User> listFilms(){

        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    User listFilms(@PathVariable("id")UUID id){

        return userRepository.findById(id);
    }
}
