package org.example.bokningssystem.controller;


import lombok.RequiredArgsConstructor;

import org.example.bokningssystem.dtos.DetailedBokningDto;
import org.example.bokningssystem.dtos.DetailedKundDto;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;

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
        List<DetailedBokningDto> bokningar = bokningService.getAllBookings();
        model.addAttribute("bokningar", bokningar);
        model.addAttribute("pageTitle", "Alla befintliga bokningar");
        model.addAttribute("tableHeadings", Arrays.asList("Kundens namn", "Rumsnamn", "Startdatum", "Slutdatum"));
        model.addAttribute("emptyListMessage", "Inga bokningar hittades");
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
        public String bookRoom(DetailedBokningDto detailedBokningDto, RedirectAttributes redirectAttributes) {

            String message = bokningService.addBokningCheck(detailedBokningDto);

            if (message.equals("upptaget")) {
                redirectAttributes.addFlashAttribute("rummetRedanBokat", "Rummet är redan bokat vid dessa datum.");
                return "redirect:/booking";
            }
            else {
                bokningService.addBokning(detailedBokningDto);
                redirectAttributes.addFlashAttribute("bokningSuccess", "Bokningen är nu tillagd i systemet.");
                return "redirect:/booking";
            }
        }


    @RequestMapping(path ="/booking/deleteById/{id}")
    public String deleteById(@PathVariable Long id) {
        bokningService.deleteBooking(id);
        return "redirect:/booking";

    }

    @PostMapping("modifyBooking")
    public String modifyBooking(DetailedBokningDto bokning) {

        bokningService.modifyBookning(bokning);

        return "redirect:/booking";
    }



}
