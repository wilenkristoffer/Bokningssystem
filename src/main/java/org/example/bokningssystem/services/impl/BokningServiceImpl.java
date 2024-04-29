package org.example.bokningssystem.services.impl;


import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.BokningDto;
import org.example.bokningssystem.dtos.DetailedBokningDto;
import org.example.bokningssystem.dtos.KundDto;
import org.example.bokningssystem.dtos.RumDto;
import org.example.bokningssystem.modell.Bokning;
import org.example.bokningssystem.modell.Kund;
import org.example.bokningssystem.modell.Rum;
import org.example.bokningssystem.repo.BokningRepo;
import org.example.bokningssystem.repo.KundRepo;
import org.example.bokningssystem.repo.RumRepo;
import org.example.bokningssystem.services.BokningService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BokningServiceImpl implements BokningService {

    private final BokningRepo bokningRepo;
    private final KundRepo kundRepo;
    private final RumRepo rumRepo;

    public BokningServiceImpl(BokningRepo bokningRepo, KundRepo kundRepo, RumRepo rumRepo){
        this.bokningRepo=bokningRepo;
        this.kundRepo=kundRepo;
        this.rumRepo=rumRepo;
    }

    @Override
    public BokningDto bokningToBokningDto(Bokning b) {
        return BokningDto.builder().id(b.getId()).build();
    }


    @Override
    public Bokning detailedBokningToDetailedBokningDto(DetailedBokningDto b) {
        return Bokning.builder().id(b.getId()).endDate(b.getEndDate()).startDate(b.getStartDate()).kund(Kund.builder()
                        .id(b.getKund().getId())
                        .namn(b.getKund().getNamn())
                        .build())
                .room(Rum.builder()
                        .id(b.getRoom().getId())
                        .name(b.getRoom().getName())
                        .build())
                .build();

    }

    @Override
    public DetailedBokningDto bokningToDetailedBokningDto(Bokning b) {
        return DetailedBokningDto.builder().id(b.getId())
                .kund(new KundDto(b.getKund().getId(), b.getKund().getNamn()))
                .room(new RumDto(b.getRoom().getId(), b.getRoom().getName())).build();
    }

    @Override
    public Bokning detailedBokningDtoToBokning(DetailedBokningDto b, Kund kund, Rum rum){
        return Bokning.builder()
                .id(b.getId())
                .startDate(b.getStartDate())
                .endDate(b.getEndDate())
                .room(rum)
                .kund(kund)
                .build();
    }
    @Override
    public String addBokning(DetailedBokningDto bokning) {
        Kund kund = kundRepo.findById(bokning.getKund().getId()).get();
        Rum rum = rumRepo.findById(bokning.getRoom().getId()).get();
        bokningRepo.save(detailedBokningDtoToBokning(bokning, kund, rum));

        return "Bokning tillagd!";
    }

    @Override
    public List<DetailedBokningDto> getAllBokning() {
        return bokningRepo.findAll().stream().map(b -> bokningToDetailedBokningDto(b)).toList();
    }
}
