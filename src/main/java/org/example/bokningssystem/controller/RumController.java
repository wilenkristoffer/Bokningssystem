package org.example.bokningssystem.controller;


import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.modell.Rum;
import org.example.bokningssystem.repo.RumRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private final RumRepo rumRepo;


    @RequestMapping("rooms")
    public String handleRooms(Model model){
        List<Rum> rummen = rumRepo.findAll();
        model.addAttribute("rummen", rummen);
        model.addAttribute("pageTitle", "Alla befintliga rum");
        model.addAttribute("tableHeadings", Arrays.asList("Rumstyp"));
        model.addAttribute("emptyListMessage", "Inga rum hittades");
        return "handleRooms.html";
    }

    @PostMapping("roomCreated")
    public String createRoom(@RequestParam String name){
        rumRepo.save(new Rum(name));
        return "redirect:/rooms";
    }


}
