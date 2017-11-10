package com.karel.video.rental.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="FILM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Float premiumPrice;
    private Float basicPrice;
    private String type;

}
