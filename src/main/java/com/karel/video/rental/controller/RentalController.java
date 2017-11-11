package com.karel.video.rental.controller;


import com.karel.video.rental.domain.*;
import com.karel.video.rental.repository.ReservationRepository;
import com.karel.video.rental.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/rent")
@Slf4j
public class RentalController {

    @Autowired
    private RentalService rentalService;
    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping(value = "/calculate")
    public @ResponseBody
    CalculateResponse makeReservation(@RequestBody CalculateRequest request){

        return rentalService.calculateRent(request.getDateIni(), request.getDateEnd(), request.getFilmId(), request.getUserId());
    }

    @PostMapping(value = "/return/{id}")
    public @ResponseBody ReturnFilmResponse returnFilm(@PathVariable("id") UUID idReservation){

        ReturnFilmResponse result = new ReturnFilmResponse();
        result.setToPay(rentalService.returnFilm(idReservation));
        return result;
    }

    @GetMapping(value = "/reservation/list")
    public @ResponseBody Iterable<Reservation> listFilms(){

        return reservationRepository.findAll();
    }
}
