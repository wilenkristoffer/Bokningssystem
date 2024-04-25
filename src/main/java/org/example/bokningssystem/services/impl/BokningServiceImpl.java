package org.example.bokningssystem.services.impl;


import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.BokningDto;
import org.example.bokningssystem.dtos.DetailedBokningDto;
import org.example.bokningssystem.dtos.KundDto;
import org.example.bokningssystem.dtos.RumDto;
import org.example.bokningssystem.modell.Bokning;
import org.example.bokningssystem.repo.BokningRepo;
import org.example.bokningssystem.services.BokningService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BokningServiceImpl implements BokningService {

    private final BokningRepo bokningRepo;

    @Override
    public BokningDto bokningToBokningDto(Bokning b) {
        return BokningDto.builder().id(b.getId()).build();
    }

    @Override
    public Bokning detailedBokningToDetailedBokningDto(DetailedBokningDto b) {
        return null;
    }

    /*@Override
    public Bokning detailedBokningToDetailedBokningDto(DetailedBokningDto b) {
        return DetailedBokningDto.builder().id(b.getId()).datum(b.getDatum()).
    }

     */

    @Override
    public DetailedBokningDto bokningToDetailedBokningDto(Bokning b) {
        return DetailedBokningDto.builder().id(b.getId())
                .kund(new KundDto(b.getKund().getId(), b.getKund().getNamn()))
                .room(new RumDto(b.getRoom().getId(), b.getRoom().getName())).build();
    }

    @Override
    public String addBokning(DetailedBokningDto bokning) {
        bokningRepo.save(detailedBokningToDetailedBokningDto(bokning));

        return "Bokning tillagd!";
    }
}
