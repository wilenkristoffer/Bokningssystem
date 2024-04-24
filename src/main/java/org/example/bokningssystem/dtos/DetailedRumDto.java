package org.example.bokningssystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bokningssystem.modell.Rumstyp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedRumDto {

    private Long id;
    private Rumstyp rumstyp;
}
