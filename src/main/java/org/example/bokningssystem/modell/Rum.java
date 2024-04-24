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
public class Rum {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rumstyp rumstyp;

    //(mappedBy = "room")
    @OneToMany(mappedBy = "room")
    private List<Bokning> bokning;




    public Rum( Rumstyp rumstyp) {
        this.rumstyp = rumstyp;
    }
}
