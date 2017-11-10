package com.karel.video.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateResponse implements Serializable {

    private String nameFilm;
    private Float price;
    private long days;
    private String userName;
    private long bonus;
}
