package org.example.bokningssystem.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BokningDto {
    private Long id;
    private int antalNatter;
    //Kan tillägga mer kanske?
}
