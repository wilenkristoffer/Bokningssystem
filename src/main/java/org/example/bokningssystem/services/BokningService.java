package org.example.bokningssystem.services;

import org.example.bokningssystem.dtos.BokningDto;
import org.example.bokningssystem.dtos.DetailedBokningDto;
import org.example.bokningssystem.modell.Bokning;
import org.example.bokningssystem.modell.Kund;
import org.example.bokningssystem.modell.Rum;

public interface BokningService {

    public BokningDto bokningToBokningDto(Bokning b);
    public Bokning detailedBokningToDetailedBokningDto(DetailedBokningDto b);
    public DetailedBokningDto bokningToDetailedBokningDto(Bokning b);

    Bokning detailedBokningDtoToBokning(DetailedBokningDto b, Kund kund, Rum rum);

    public String addBokning(DetailedBokningDto bokning);
}
