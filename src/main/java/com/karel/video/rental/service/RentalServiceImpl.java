package com.karel.video.rental.service;

import com.karel.video.rental.domain.CalculateResponse;
import com.karel.video.rental.domain.FILM_TYPE;
import com.karel.video.rental.domain.Film;
import com.karel.video.rental.domain.User;
import com.karel.video.rental.repository.FilmRepository;
import com.karel.video.rental.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
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

        Float price = 0f;
        long bonus = 0;
        CalculateResponse response ;
        User user = userRepository.findById(userId);
        Film film = filmRepository.findById(filmId);
        long days = ChronoUnit.DAYS.between(dateIni, dateEnd);

        if( film.getType().equalsIgnoreCase(FILM_TYPE.PREMIUM.toString())){

            price = film.getPremiumPrice() * days;
            bonus += 2;

        }else if(film.getType().equalsIgnoreCase(FILM_TYPE.REGULAR.toString())){

            long restDays = days -3;
            price = film.getBasicPrice() + film.getBasicPrice() * (( restDays > 0)? restDays : 0);
            bonus++;

        }else if(film.getType().equalsIgnoreCase(FILM_TYPE.OLD.toString())){

            long restDays = days -5;
            price = film.getBasicPrice() + film.getBasicPrice() * (( restDays > 0)? restDays : 0);
        }

        if(0 != bonus && null != user){
            user.setBonus( user.getBonus() + bonus);
            user = userRepository.save(user);
        }
        response = new CalculateResponse(film.getName(), price, days, user.getName(), user.getBonus());

        return response;
    }
}
