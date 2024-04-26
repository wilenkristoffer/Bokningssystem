package org.example.bokningssystem.controller;


import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedKundDto;
import org.example.bokningssystem.modell.Kund;
import org.example.bokningssystem.repo.KundRepo;
import org.example.bokningssystem.services.KundService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class KundController {

    private final KundService kundService;


    @RequestMapping("customer")
    public String handleCustomer(Model model) {
        List<DetailedKundDto> kunder = kundService.getAllKunder();
        model.addAttribute("kunder", kunder);
        model.addAttribute("pageTitle", "Alla befintliga kunder");
        model.addAttribute("tableHeadings", Arrays.asList("Namn", "Email", "Telefon", "Personnummer"));
        model.addAttribute("emptyListMessage", "Inga kunder hittades");
        return "handleCustomer.html";
    }

    @PostMapping("customerCreated")
    public String create(DetailedKundDto kund, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage", "Kunden har lagts till!");
        kundService.addKund(kund);
        return "redirect:/customer";
    }

    @PostMapping("modifyCustomer")
    public String modifyCustomer(DetailedKundDto kund) {

        kundService.modifyKund(kund);

        return "redirect:/customer";
    }


    @RequestMapping(path ="/customer/deleteById/{id}")
    public String deleteById(@PathVariable Long id) {
        kundService.deleteCustomer(id);
        return "redirect:/customer";

    }
}
