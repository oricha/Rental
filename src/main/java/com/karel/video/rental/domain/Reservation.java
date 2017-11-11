package com.karel.video.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="RESERVATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID userId;
    private UUID filmId;
    private Float price;
    private LocalDate dateIni;
    private LocalDate dateEnd;
    private LocalDate returnEnd;

    public Reservation (UUID userId, UUID filmId, LocalDate dateIni, LocalDate dateEnd, Float price){
        super();
        this.userId = userId;
        this.filmId = filmId;
        this.dateIni = dateIni;
        this.dateEnd = dateEnd;
        this.price = price;
    }
}
