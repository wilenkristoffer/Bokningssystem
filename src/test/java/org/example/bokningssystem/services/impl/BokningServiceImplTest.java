package org.example.bokningssystem.services.impl;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BokningServiceImplTest {

 @MockBean
 private BokningRepo bokningRepo;

 @MockBean
 private KundRepo kundRepo;

 @MockBean
 private RumRepo rumRepo;

 @Autowired
 private BokningServiceImpl bokningService;

 private Rum rum;
 private Kund kund;
 private Bokning bokning;

 @BeforeEach
 void init() {
  kund = new Kund();
  rum = new Rum();
  bokning = new Bokning();
 }

 @Test
 public void testBokningToBokningDto() {
  bokning.setId(1L);
  when(bokningRepo.findById(1L)).thenReturn(Optional.of(bokning));

  BokningDto bokningDto = bokningService.bokningToBokningDto(bokning);

  assertEquals(1L, bokningDto.getId());
 }

 @Test
 public void testBokningToDetailedBokningDto() {
  bokning.setId(1L);
  bokning.setStartDate(LocalDate.parse("2024-09-01"));
  bokning.setEndDate(LocalDate.parse("2024-09-14"));

  kund.setId(1L);
  kund.setNamn("Test Kund");

  rum.setId(1L);
  rum.setName("Test Room");

  bokning.setKund(kund);
  bokning.setRoom(rum);

  when(kundRepo.findById(1L)).thenReturn(Optional.of(kund));
  when(rumRepo.findById(1L)).thenReturn(Optional.of(rum));

  DetailedBokningDto detailedBokningDto = bokningService.bokningToDetailedBokningDto(bokning);

  assertEquals(1L, detailedBokningDto.getId());
  assertEquals(LocalDate.parse("2024-09-01"), detailedBokningDto.getStartDate());
  assertEquals(LocalDate.parse("2024-09-14"), detailedBokningDto.getEndDate());
  assertEquals(1L, detailedBokningDto.getKund().getId());
  assertEquals("Test Kund", detailedBokningDto.getKund().getNamn());
  assertEquals(1L, detailedBokningDto.getRoom().getId());
  assertEquals("Test Room", detailedBokningDto.getRoom().getName());
 }

 @Test
 public void testAddBokning() {

  DetailedBokningDto bokningDto = new DetailedBokningDto();
  bokningDto.setStartDate(LocalDate.now().plusDays(1));
  bokningDto.setEndDate(LocalDate.now().plusDays(5));
  bokningDto.setKund(new KundDto(1L, "Testkund"));
  bokningDto.setRoom(new RumDto(1L, "Testrum"));

  when(kundRepo.findById(anyLong())).thenReturn(Optional.of(kund));
  when(rumRepo.findById(anyLong())).thenReturn(Optional.of(rum));

  String result = bokningService.addBokning(bokningDto);

  assertEquals("Bokning tillagd", result);
 }
}
