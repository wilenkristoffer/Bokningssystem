package org.example.bokningssystem.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedBokningDto {

    private Long id;
    private LocalDate datum;
    private int antalNatter;
    private KundDto kund;
    private RumDto room;
}
