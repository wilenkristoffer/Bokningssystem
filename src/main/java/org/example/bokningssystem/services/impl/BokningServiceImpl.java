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
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BokningServiceImpl implements BokningService {

    private final BokningRepo bokningRepo;
    private final KundRepo kundRepo;
    private final RumRepo rumRepo;


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
                .room(new RumDto(b.getRoom().getId(), b.getRoom().getName()))
                .startDate(b.getStartDate())
                .endDate(b.getEndDate())
                .build();
    }

    @Override
    public List<DetailedBokningDto> getAllBookings() {
        return bokningRepo.findAll().stream()
                .map(b -> bokningToDetailedBokningDto(b)).toList();
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
        return "Bokning tillagd";
    }

    @Override
    public String addBokningCheck(DetailedBokningDto bokning) {
        Rum rum = rumRepo.findById(bokning.getRoom().getId()).get();

        List<DetailedBokningDto> bookingsForRoom = getAllBookings().stream()
                .filter(b -> b.getRoom().getId().equals(rum.getId()))
                .toList();

        boolean isDatesAvailable = bookingsForRoom.stream().noneMatch(existingBooking -> {
            boolean overlaps = !existingBooking.getEndDate().isBefore(bokning.getStartDate())
                    && !bokning.getEndDate().isBefore(existingBooking.getStartDate());
            return overlaps;
        });

        if (!isDatesAvailable) {
            return "upptaget";
        } else {
            return "fri";
        }
    }

    @Override
    public String modifyBookning(DetailedBokningDto updatedBokning) {
        Optional<Bokning> optionalBokning = bokningRepo.findById(updatedBokning.getId());
        Optional<Kund> optionalKund = kundRepo.findById(updatedBokning.getKund().getId());
        Optional<Rum> optionalRum = rumRepo.findById(updatedBokning.getRoom().getId());
        Bokning existingBokning = optionalBokning.get();
        Kund kund = optionalKund.get();
        Rum rum = optionalRum.get();

        existingBokning.setStartDate(updatedBokning.getStartDate());
        existingBokning.setEndDate(updatedBokning.getEndDate());
        existingBokning.setKund(kund);
        existingBokning.setRoom(rum);

        bokningRepo.save(existingBokning);

        return "Bokningen har uppdaterats!";
    }

    @Override
    public String deleteBooking(Long bokningId) {
        bokningRepo.deleteById(bokningId);
        return "kunden har tagit borts";
    }

}
