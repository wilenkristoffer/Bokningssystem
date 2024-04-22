package org.example.bokningssystem.modell;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Kund {
    @Id
    @GeneratedValue
    private Long id;
    private String namn;
    private String email;
    private String telefonNr;
    private String personummer;


    public Kund(String namn, String email, String telefonNr, String personummer) {
        this.namn = namn;
        this.email = email;
        this.telefonNr = telefonNr;
        this.personummer = personummer;
    }


}
