package org.example.bokningssystem.modell;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Bokning {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate datum;
    private int antalNatter;


    public Bokning(LocalDate datum, int antalNatter) {
        this.datum = datum;
        this.antalNatter = antalNatter;

    }
}

