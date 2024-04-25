package org.example.bokningssystem.services;


import org.example.bokningssystem.dtos.DetailedRumDto;
import org.example.bokningssystem.dtos.RumDto;
import org.example.bokningssystem.modell.Rum;

import java.util.List;

public interface RumService {

    public RumDto rumToRumDto(Rum r);
    public DetailedRumDto rumToDetailedRumDto(Rum r);
    public List<RumDto> getAllRumSimple();
}
