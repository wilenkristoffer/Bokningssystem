package org.example.bokningssystem.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bokningssystem.modell.Bokning;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedKundDto {
    private Long id;

    @NotEmpty(message = "Måste fylla i fältet")
    @Size(min = 3, message = "Minst 3 bokstäver")
    @Pattern(regexp="^[A-Öa-ö ]*$", message = "Bara bokstäver för namn")
    private String namn;

    @NotEmpty(message = "Måste fylla i fältet")
    @Email
    private String email;

    @NotEmpty(message = "Måste fylla i fältet")
    @NumberFormat
    @Size(min = 10, max = 15)
    private String telefonNr;

    @NotEmpty(message = "Måste fylla i fältet")
    @NumberFormat
    @Size(min = 10, max = 12)
    private String personummer;

    private List<BokningDto> bokning;


}
