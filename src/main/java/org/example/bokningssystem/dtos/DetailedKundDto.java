package org.example.bokningssystem.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bokningssystem.modell.Bokning;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedKundDto {
    private Long id;
    private String namn;
    private String email;
    private String telefonNr;
    private String personummer;
    private List<BokningDto> bokning;












}
