package org.example.bokningssystem.modell;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "Måste fylla i fältet")
    @Size(min = 3, message = "Minst 3 bokstäver")
    private String name;

    @Enumerated(EnumType.STRING)
    private Rumstyp rumstyp;

    private int antalSangar;

    @OneToMany(mappedBy = "room")
    private List<Bokning> rumBokning;


}
