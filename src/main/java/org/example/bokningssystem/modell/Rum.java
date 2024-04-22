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
public class Rum {

    @Id
    @GeneratedValue
    private Long id;
    private boolean bokad;
    private Rumstyp rumstyp;


    public Rum(boolean bokad, Rumstyp rumstyp) {
        this.bokad = bokad;
        this.rumstyp = rumstyp;
    }
}
