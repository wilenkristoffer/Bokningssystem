package org.example.bokningssystem.modell;


import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Rumstyp rumstyp;


    public Rum( Rumstyp rumstyp) {
        this.rumstyp = rumstyp;
    }
}
