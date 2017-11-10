package com.karel.video.rental.repository;

import com.karel.video.rental.domain.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface FilmRepository extends CrudRepository<Film, Long> {


    Film findByName(String name);
    Film findById(UUID id);
}
