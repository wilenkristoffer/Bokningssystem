package org.example.bokningssystem.controller;

import org.example.bokningssystem.dtos.*;
import org.example.bokningssystem.repo.EventRepo;
import org.example.bokningssystem.modell.Rum;
import org.example.bokningssystem.repo.RumRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    private final EventRepo eventRepo;
    private final RumRepo rumRepo;

    public EventController(EventRepo eventRepo, RumRepo rumRepo) {
        this.eventRepo = eventRepo;
        this.rumRepo = rumRepo;
    }

    @GetMapping("/roomDetails")
    public String handleRoomDetails(@RequestParam Long roomId, Model model) {
        Rum rum = rumRepo.findById(roomId).orElse(null);

        List<RoomEvent> events = new ArrayList<>();
        events.addAll(eventRepo.findClosedEventsByRoomNo(String.valueOf(rum.getId())));
        events.addAll(eventRepo.findOpenedEventsByRoomNo(String.valueOf(rum.getId())));
        events.addAll(eventRepo.findCleaningStartedEventsByRoomNo(String.valueOf(rum.getId())));
        events.addAll(eventRepo.findCleaningFinishedEventsByRoomNo(String.valueOf(rum.getId())));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<String> formattedTimestamps = new ArrayList<>();
        for (RoomEvent event : events) {
            String formattedTimestamp = event.getTimeStamp().format(formatter);
            formattedTimestamps.add(formattedTimestamp);
        }
        model.addAttribute("formattedTimestamps", formattedTimestamps);


        model.addAttribute("rum", rum);
        model.addAttribute("events", events);
        return "roomDetails";
    }
}