package org.example.bokningssystem.controller;


import lombok.RequiredArgsConstructor;

import org.example.bokningssystem.dtos.*;
import org.example.bokningssystem.services.BokningService;
import org.example.bokningssystem.services.KundService;
import org.example.bokningssystem.services.RumService;
import org.example.bokningssystem.services.impl.BlackListServiceImpl;
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
    private final BlackListServiceImpl blackListServiceImpl;

    @RequestMapping("booking")
    public String handleBooking(Model model) {
        List<DetailedBokningDto> bokningar = bokningService.getAllBookings();
        List<KundDto> kunder = kundService.getAllKundSimple();
        List<RumDto> rummen = rumService.getAllRumSimple();

        model.addAttribute("kunder", kunder);
        model.addAttribute("rummen", rummen);


        // Bokningar
        model.addAttribute("bokningar", bokningar);
        model.addAttribute("pageTitle", "Alla befintliga bokningar");
        model.addAttribute("tableHeadings", Arrays.asList("Kundens namn", "Rumsnamn", "Startdatum", "Slutdatum"));
        model.addAttribute("emptyListMessage", "Inga bokningar hittades");



        // Kunder
        model.addAttribute("customerPageTitle", "Alla kunder");
        model.addAttribute("customerEmptyListMessage", "Inga kunder hittades");

        // Rum
        model.addAttribute("roomPageTitle", "Alla rum");
        model.addAttribute("roomEmptyListMessage", "Inga rum hittades");
        model.addAttribute("availableRoomPageTitle", "Alla lediga rum");
        model.addAttribute("availableRoomEmptyListMessage", "Inga lediga rum hittades");
        return "handleBooking.html";
    }



    @PostMapping("/bookRoom")
    public String bookRoom(DetailedBokningDto detailedBokningDto, RedirectAttributes redirectAttributes) {
        String customerEmail = detailedBokningDto.getKund().getEmail();

        // Log the email being checked
        System.out.println("Attempting to book room for " + customerEmail);

        // Check if the customer is blacklisted
        if (blackListServiceImpl.isCustomerBlacklisted(customerEmail)) {
            System.out.println("Booking failed: " + customerEmail + " is blacklisted.");
            redirectAttributes.addFlashAttribute("blacklistError", "The customer is blacklisted and cannot book.");
            return "redirect:/booking";
        } else {
            bokningService.addBokning(detailedBokningDto);
            System.out.println("Booking successful for " + customerEmail);
            redirectAttributes.addFlashAttribute("bokningSuccess", "The booking has been successfully added.");
            return "redirect:/booking";
        }
    }

    /*
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

 */


    @RequestMapping(path ="/booking/deleteById/{id}")
    public String deleteById(@PathVariable Long id) {
        bokningService.deleteBooking(id);
        return "redirect:/booking";

    }

    @PostMapping("modifyBooking")
    public String modifyBooking(DetailedBokningDto bokning, RedirectAttributes redirectAttributes) {

        String message = bokningService.addBokningCheck(bokning);

        if (message.equals("upptaget")) {
            redirectAttributes.addFlashAttribute("rummetRedanBokat", "Rummet är redan bokat vid dessa datum.");
            return "redirect:/booking";
        }
        else {
            bokningService.modifyBookning(bokning);
            redirectAttributes.addFlashAttribute("modifySuccess", "Bokningen är nu ändrad.");
            return "redirect:/booking";
        }
    }

    @PostMapping("searchDate")
    public String searchDate(SearchBokningDto searchBokningDto, RedirectAttributes redirectAttributes){

        List<RumDto> ledigaRum = rumService.getAvailableRum(searchBokningDto);

        redirectAttributes.addFlashAttribute("ledigaRum", ledigaRum);


        return "redirect:/booking";
    }



}
