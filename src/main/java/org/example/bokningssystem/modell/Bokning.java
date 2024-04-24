package org.example.bokningssystem.modell;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Bokning {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate datum;
    private int antalNatter;

    @ManyToOne
    @JoinColumn
    private Rum room;

    @ManyToOne
    @JoinColumn
    private Kund kund;


    public Bokning(LocalDate datum, int antalNatter) {
        this.datum = datum;
        this.antalNatter = antalNatter;

    }
}

