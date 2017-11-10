package com.karel.video.rental.service;

import com.karel.video.rental.domain.CalculateResponse;

import java.time.LocalDate;
import java.util.UUID;


public interface RentalService {


    CalculateResponse calculateRent(LocalDate dateIn, LocalDate dateOut, UUID filmId, UUID userId);

}
