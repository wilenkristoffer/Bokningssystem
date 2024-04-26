package org.example.bokningssystem.services.impl;


import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedKundDto;
import org.example.bokningssystem.dtos.KundDto;
import org.example.bokningssystem.modell.Kund;
import org.example.bokningssystem.repo.KundRepo;
import org.example.bokningssystem.services.BokningService;
import org.example.bokningssystem.services.KundService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KundServiceImpl implements KundService {

    private final KundRepo kundRepo;
    private final BokningService bokningService;

    @Override
    public KundDto kundToKundDto(Kund k) {
        return KundDto.builder().id(k.getId())
                .namn(k.getNamn()).build();
    }

    @Override
    public DetailedKundDto kundToDetailedKundDto(Kund k) {
        return DetailedKundDto.builder().id(k.getId())
                .namn(k.getNamn()).email(k.getEmail())
                .telefonNr(k.getTelefonNr()).personummer(k.getPersonummer())
                .bokning(k.getKundBokning().stream()
                        .map(bokning -> bokningService.bokningToBokningDto(bokning)).toList())
                .build();
    }

    @Override
    public Kund detailedKundToDetailedKundDto(DetailedKundDto k) {
        return Kund.builder().id(k.getId())
                .namn(k.getNamn()).email(k.getEmail())
                .telefonNr(k.getTelefonNr()).personummer(k.getPersonummer())
                .build();
    }

    @Override
    public List<DetailedKundDto> getAllKunder() {
        return kundRepo.findAll().stream()
                .map(k -> kundToDetailedKundDto(k)).toList();
    }

    @Override
    public List<KundDto> getAllKundSimple() {
        return kundRepo.findAll().stream().map(k -> kundToKundDto(k)).toList();
    }

    @Override
    public String addKund(DetailedKundDto kund) {
        kundRepo.save(detailedKundToDetailedKundDto(kund));
        return "Kunden har sparats!!!!";
    }

    @Override
    public String modifyKund(DetailedKundDto updatedKund) {

        Optional<Kund> optionalKund = kundRepo.findById(updatedKund.getId());

            Kund existingKund = optionalKund.get();


            existingKund.setNamn(updatedKund.getNamn());
            existingKund.setEmail(updatedKund.getEmail());
            existingKund.setTelefonNr(updatedKund.getTelefonNr());
            existingKund.setPersonummer(updatedKund.getPersonummer());

            kundRepo.save(existingKund);

            return "Kunden har uppdaterats!";
        }

    @Override
    public String deleteCustomer(Long customerId) {
            kundRepo.deleteById(customerId);
            return "Kunden har raderats!";
        }

}
