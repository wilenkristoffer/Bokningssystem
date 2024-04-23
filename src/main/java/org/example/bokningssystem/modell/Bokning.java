package org.example.bokningssystem.modell;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    @JoinColumn
    private Rum room;

    public Bokning(LocalDate datum, int antalNatter) {
        this.datum = datum;
        this.antalNatter = antalNatter;

    }
}

