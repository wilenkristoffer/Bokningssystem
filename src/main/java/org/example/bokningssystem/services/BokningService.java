package org.example.bokningssystem.services;

import org.example.bokningssystem.dtos.BokningDto;
import org.example.bokningssystem.dtos.DetailedBokningDto;
import org.example.bokningssystem.modell.Bokning;

import java.util.List;

public interface BokningService {

    public BokningDto bokningToBokningDto(Bokning b);
    public Bokning detailedBokningToDetailedBokningDto(DetailedBokningDto b);
    public DetailedBokningDto bokningToDetailedBokningDto(Bokning b);
    public String addBokning(DetailedBokningDto bokning);
}
