package org.example.bokningssystem.services;

import org.example.bokningssystem.dtos.DetailedKundDto;
import org.example.bokningssystem.dtos.KundDto;
import org.example.bokningssystem.modell.Kund;

import java.util.List;

public interface KundService {

    public KundDto kundToKundDto(Kund k);
    public DetailedKundDto kundToDetailedKundDto(Kund k);
    public List<DetailedKundDto> getAllKunder();
}
