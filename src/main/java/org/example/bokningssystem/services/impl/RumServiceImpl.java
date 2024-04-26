package org.example.bokningssystem.services.impl;


import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedRumDto;
import org.example.bokningssystem.dtos.RumDto;
import org.example.bokningssystem.modell.Rum;
import org.example.bokningssystem.repo.RumRepo;
import org.example.bokningssystem.services.BokningService;
import org.example.bokningssystem.services.RumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RumServiceImpl implements RumService {

    private final RumRepo rumRepo;
    private final BokningService bokningService;

    @Override
    public RumDto rumToRumDto(Rum r) {
        return RumDto.builder().id(r.getId()).name(r.getName()).build();
    }

    @Override
    public DetailedRumDto rumToDetailedRumDto(Rum r) {
        return DetailedRumDto.builder().id(r.getId())
                .name(r.getName())
                .bokning(r.getRumBokning().stream()
                        .map(bo -> bokningService.bokningToBokningDto(bo)).toList())
                .build();
    }

    @Override
    public Rum detailedRumToDetailedRumDto(DetailedRumDto r) {
        return Rum.builder().id(r.getId())
                .name(r.getName())
                .build();
    }


    @Override
    public List<RumDto> getAllRumSimple() {
        return rumRepo.findAll().stream().map(r -> rumToRumDto(r)).toList();
    }

    @Override
    public List<DetailedRumDto> getAllRooms() {
        return rumRepo.findAll().stream()
                .map(r -> rumToDetailedRumDto(r)).toList();
    }

    @Override
    public String addRum(DetailedRumDto rum) {
        rumRepo.save(detailedRumToDetailedRumDto(rum));
        return "Rum har skapats";
    }


}
