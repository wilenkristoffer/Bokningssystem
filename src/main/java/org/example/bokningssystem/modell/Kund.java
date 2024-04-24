package org.example.bokningssystem.modell;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Kund {
    @Id
    @GeneratedValue
    private Long id;
    private String namn;
    private String email;
    private String telefonNr;
    private String personummer;

    //(mappedBy = "kund")
    @OneToMany(mappedBy = "kund")
    private List<Bokning> kundBokning;


    public Kund(String namn, String email, String telefonNr, String personummer) {
        this.namn = namn;
        this.email = email;
        this.telefonNr = telefonNr;
        this.personummer = personummer;
    }


}
