package org.example.bokningssystem.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedRumDto;
import org.example.bokningssystem.modell.Rum;
import org.example.bokningssystem.repo.RumRepo;
import org.example.bokningssystem.services.RumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RumController {

    private final RumService rumService;


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




}
