package com.karel.video.rental.controller;

import com.karel.video.rental.domain.Film;
import com.karel.video.rental.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/film")
@Slf4j
public class FilmControler {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping(value = "/list")
    public @ResponseBody Iterable<Film> listFilms(){

        return filmRepository.findAll();
    }

    @PutMapping(value = "/add")
    public @ResponseBody Film addFilm(@RequestBody Film film){

        return filmRepository.save(film);
    }


}
