package com.karel.video.rental.service;

import com.karel.video.rental.domain.*;
import com.karel.video.rental.repository.FilmRepository;
import com.karel.video.rental.repository.ReservationRepository;
import com.karel.video.rental.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Slf4j
public class RentalServiceImpl  implements RentalService {


    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * New releases - Price is <premium price> times number of days rented.
     * Regular films - Price is <basic price> for the first 3 days and then <basic price> times the number of days over 3.
     * Old film - Price is <basic price> for the first 5 days and then <basic price> times the number of days over 5
     * @param dateIni
     * @param dateEnd
     * @param filmId
     * @return
     */
    @Override
    public CalculateResponse calculateRent(LocalDate dateIni, LocalDate dateEnd, UUID filmId, UUID userId) {

        CalculateResponse response ;
        User user = userRepository.findById(userId);
        Film film = filmRepository.findById(filmId);
        long days = ChronoUnit.DAYS.between(dateIni, dateEnd);
        Float price = calculatePrice(film, days );
        long bonus = calculateBonus(film);
        if(0 != bonus && null != user){
            user.setBonus( user.getBonus() + bonus);
            user = userRepository.save(user);
        }

        Reservation reservation = reservationRepository.save( new Reservation(userId, filmId, dateIni, dateEnd, price));
        response = new CalculateResponse(film.getName(), price, days, user.getName(), user.getBonus(), reservation.getId());

        return response;
    }

    public Float returnFilm(UUID idReservation){

        Float returnPrice = 0f;
        Reservation reservation = reservationRepository.findOne(idReservation);
        returnPrice = reservation.getPrice();
        Film film = filmRepository.findById(reservation.getFilmId());
        long days = ChronoUnit.DAYS.between(reservation.getDateIni(), reservation.getDateIni());

        if( 0 < ChronoUnit.DAYS.between(reservation.getDateEnd(), LocalDate.now()) ){
            days = ChronoUnit.DAYS.between(reservation.getDateIni(), LocalDate.now());
            returnPrice =  calculatePrice(film, days );
        }

        return returnPrice;
    }

    private Float calculatePrice(Film film, long days){
        Float result = 0f;
        if(film.getType().equalsIgnoreCase(FILM_TYPE.PREMIUM.toString())){
            result = film.getPremiumPrice() * days;
        }else if(film.getType().equalsIgnoreCase(FILM_TYPE.REGULAR.toString())){
            long restDays = days -3;
            result = film.getBasicPrice() + film.getBasicPrice()  * (( restDays > 0)? restDays : 0);

        }else if(film.getType().equalsIgnoreCase(FILM_TYPE.OLD.toString())){
            long restDays = days -5;
            result = film.getBasicPrice()  + film.getBasicPrice()  * (( restDays > 0)? restDays : 0);
        }
        return result;
    }

    private long calculateBonus(Film film){
        long bonus = 0;
        if(film.getType().equalsIgnoreCase(FILM_TYPE.PREMIUM.toString())){
            bonus += 2;
        }else if(film.getType().equalsIgnoreCase(FILM_TYPE.REGULAR.toString())){
            bonus++;
        }
        return bonus;
    }
}
