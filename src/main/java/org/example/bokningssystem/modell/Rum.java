package org.example.bokningssystem.modell;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

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

    @NotNull(message = "Rumstyp får inte vara null")
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Rumstyp rumstyp;

    @Size(max = 2, message = "Ska vara mellan 0 till 2")
    @Positive(message = "Värdet måste vara positivt")
    private int antalSangar;

    @OneToMany(mappedBy = "room")
    private List<Bokning> rumBokning;


}
