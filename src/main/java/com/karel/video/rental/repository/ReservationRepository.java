package com.karel.video.rental.repository;

import com.karel.video.rental.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface ReservationRepository extends CrudRepository<Reservation, UUID> {

    Reservation findByUserIdAndFilmIdAndDateIni(UUID userId , UUID filmId, LocalDate dateIni);
}
