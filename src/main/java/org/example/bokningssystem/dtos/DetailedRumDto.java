package org.example.bokningssystem.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bokningssystem.modell.Bokning;
import org.example.bokningssystem.modell.Rumstyp;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedRumDto {

    private Long id;
    @NotEmpty(message = "Måste fylla i fältet")
    @Size(min = 3, message = "Minst 3 bokstäver")
    private String name;
    private List<BokningDto> bokning;
}
