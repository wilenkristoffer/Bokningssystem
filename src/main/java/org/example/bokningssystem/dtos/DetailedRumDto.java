package org.example.bokningssystem.dtos;

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
    private Rumstyp rumstyp;
    private List<Bokning> bokning;
}
