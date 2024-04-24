package org.example.bokningssystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedKundDto;
import org.example.bokningssystem.services.KundService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class KundController {

    private final KundService kundService;

    //Visa alla kunder
    @RequestMapping("/kunder")
    public List<DetailedKundDto> getAllKunder(){
        return kundService.getAllKunder();
    }
}
