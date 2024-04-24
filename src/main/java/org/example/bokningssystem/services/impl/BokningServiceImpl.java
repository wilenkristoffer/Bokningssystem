package org.example.bokningssystem.services.impl;


import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.BokningDto;
import org.example.bokningssystem.dtos.DetailedBokningDto;
import org.example.bokningssystem.dtos.KundDto;
import org.example.bokningssystem.dtos.RumDto;
import org.example.bokningssystem.modell.Bokning;
import org.example.bokningssystem.services.BokningService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BokningServiceImpl implements BokningService {
    @Override
    public BokningDto bokningToBokningDto(Bokning b) {
        return BokningDto.builder().id(b.getId())
                .antalNatter(b.getAntalNatter()).build();
    }

    @Override
    public DetailedBokningDto bokningToDetailedBokningDto(Bokning b) {
        return DetailedBokningDto.builder().id(b.getId())
                .datum(b.getDatum()).antalNatter(b.getAntalNatter())
                .kund(new KundDto(b.getKund().getId(), b.getKund().getNamn()))
                .room(new RumDto(b.getRoom().getId()))
                .build();
    }
}
