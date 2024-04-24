package org.example.bokningssystem.services.impl;


import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedRumDto;
import org.example.bokningssystem.dtos.RumDto;
import org.example.bokningssystem.modell.Rum;
import org.example.bokningssystem.services.RumService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RumServiceImpl implements RumService {
    @Override
    public RumDto rumToRumDto(Rum r) {
        return RumDto.builder().id(r.getId()).build();
    }

    @Override
    public DetailedRumDto rumToDetailedRumDto(Rum r) {
        return DetailedRumDto.builder().id(r.getId())
                .build();
    }
/*
    @Override
    public DetailedRumDto rumToDetailedRumDto(Rum r) {
        return DetailedRumDto.builder().id(r.getId())
                .rumstyp(r.getRumstyp()).build();
    }

 */
}
