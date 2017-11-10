package com.karel.video.rental.controller;


import com.karel.video.rental.domain.CalculateRequest;
import com.karel.video.rental.domain.CalculateResponse;
import com.karel.video.rental.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/rent")
@Slf4j
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping(value = "/calculate")
    public @ResponseBody
    CalculateResponse addFilm(@RequestBody CalculateRequest request){

        return rentalService.calculateRent(request.getDateIni(), request.getDateEnd(), request.getFilmId(), request.getUserId());
    }

    @PostMapping(value = "/test")
    void test(@RequestParam(name="dateIni") Date dateIni){

        log.info( dateIni.toString());
    }
}
