package org.example.bokningssystem.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedEventDto;
import org.example.bokningssystem.dtos.DetailedKundDto;
import org.example.bokningssystem.dtos.DetailedRumDto;
import org.example.bokningssystem.modell.Event;
import org.example.bokningssystem.modell.Rum;
import org.example.bokningssystem.repo.RumRepo;
import org.example.bokningssystem.services.EventService;
import org.example.bokningssystem.services.RumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class RumController {

    private final RumService rumService;
    private final EventService eventService;


    @RequestMapping("rooms")
    public String handleRooms(Model model){
        List<DetailedRumDto> rummen = rumService.getAllRooms();
        model.addAttribute("rummen", rummen);
        model.addAttribute("pageTitle", "Alla befintliga rum");
        model.addAttribute("emptyListMessage", "Inga rum hittades");
        return "handleRooms.html";
    }

    @PostMapping("roomCreated")
    public String createRoom(@Valid DetailedRumDto rum, BindingResult result,
                             Model model){
        if (result.hasErrors()) {
            model.addAttribute("rum", rum);
            model.addAttribute("rummen", rumService.getAllRooms());
            model.addAttribute("pageTitle", "Alla befintliga rum");
            model.addAttribute("emptyListMessage", "Inga rum hittades");
            return "handleRooms.html";
        }
        rumService.addRum(rum);
        return "redirect:/rooms";
    }

    @PostMapping("addbeds")
    public String addBeds(DetailedRumDto rum) {

        rumService.addBeds(rum);

        return "redirect:/rooms";
    }

    @GetMapping("/roomDetails")
    public String handleRoomDetails(Long roomId, Model model) {
        Rum rum = rumService.getRumById(roomId);
        List<Event> events = eventService.getAllEventsForRum(rum);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        List<String> formattedTimestamps = events.stream()
                .map(event -> event.getTimestamp().format(formatter))
                .collect(Collectors.toList());

        model.addAttribute("rum", rum);
        model.addAttribute("events", events);
        model.addAttribute("formattedTimestamps", formattedTimestamps);

        return "roomDetails";
    }





}
