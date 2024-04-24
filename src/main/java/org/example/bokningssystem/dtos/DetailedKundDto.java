package org.example.bokningssystem.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
