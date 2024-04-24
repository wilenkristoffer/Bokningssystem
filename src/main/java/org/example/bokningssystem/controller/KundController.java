package org.example.bokningssystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedKundDto;
import org.example.bokningssystem.services.KundService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KundController {

    private final KundService kundService;

    //Visa alla kunder
    @RequestMapping("/kunder")
    public List<DetailedKundDto> getAllKunder(){
        return kundService.getAllKunder();
    }

    //Skapa kund
    @PostMapping("kunder/add")
    public String addKund(@RequestBody DetailedKundDto kund){
        return kundService.addKund(kund);
    }

}
