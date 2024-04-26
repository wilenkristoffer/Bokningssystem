package org.example.bokningssystem.controller;


import lombok.RequiredArgsConstructor;

import org.example.bokningssystem.dtos.DetailedBokningDto;
import org.example.bokningssystem.dtos.KundDto;
import org.example.bokningssystem.dtos.RumDto;
import org.example.bokningssystem.modell.Kund;
import org.example.bokningssystem.modell.Rum;
import org.example.bokningssystem.repo.KundRepo;
import org.example.bokningssystem.repo.RumRepo;
import org.example.bokningssystem.services.BokningService;
import org.example.bokningssystem.services.KundService;
import org.example.bokningssystem.services.RumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BokningController {

    private final BokningService bokningService;
    private final KundService kundService;
    private final RumService rumService;
    private final RumRepo rumRepo;
    private final KundRepo kundRepo;

    @RequestMapping("booking")
    public String handleBooking(Model model) {
        List<KundDto> kunder = kundService.getAllKundSimple();
        List<RumDto> rummen = rumService.getAllRumSimple();
        model.addAttribute("kunder", kunder);
        model.addAttribute("rummen", rummen);
        // Kunder
        model.addAttribute("customerPageTitle", "Alla kunder");
        model.addAttribute("customerEmptyListMessage", "Inga kunder hittades");

        // Rum
        model.addAttribute("roomPageTitle", "Alla rum");
        model.addAttribute("roomEmptyListMessage", "Inga rum hittades");
        return "handleBooking.html";
    }


        @PostMapping("/bookRoom")
        public String bookRoom(DetailedBokningDto detailedBokningDto) {

            bokningService.addBokning(detailedBokningDto);

            return "redirect:/booking";
        }
}
