package com.karel.video.rental.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CalculateRequest implements Serializable{


    private UUID userId;
    private UUID filmId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateIni;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    public CalculateRequest(LocalDate dateIni, LocalDate dateEnd, UUID userId, UUID filmId){
        super();
        this.dateIni = dateIni;
        this.dateEnd = dateEnd;
        this.userId = userId;
        this.filmId = filmId;
    }
}
