package org.example.bokningssystem.controller;


import org.example.bokningssystem.modell.Kund;
import org.example.bokningssystem.repo.KundRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class KundController {

    KundRepo kundRepo;

    public KundController(KundRepo kundRepo) {
        this.kundRepo = kundRepo;
    }

    @RequestMapping("customer")
    public String handleCustomer(Model model) {
        List<Kund> kunder = kundRepo.findAll();
        model.addAttribute("kunder", kunder);
        model.addAttribute("pageTitle", "Alla befintliga kunder");
        model.addAttribute("tableHeadings", Arrays.asList("Namn", "Email", "Telefon", "Personnummer"));
        model.addAttribute("emptyListMessage", "Inga kunder hittades");
        return "handleCustomer.html";
    }

    @PostMapping("customerCreated")
    public String create(@RequestParam String name, @RequestParam String email,
                         @RequestParam String phone, @RequestParam String ssn,
                         RedirectAttributes redirectAttributes) {

        kundRepo.save(new Kund(name, email, phone, ssn));


        redirectAttributes.addFlashAttribute("successMessage", "Kunden har lagts till!");


        return "redirect:/customer";
    }

    @PostMapping("modifyCustomer")
    public String modifyCustomer(@RequestParam("id") Long id,
                                 @RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("ssn") String ssn) {
        Kund kund = kundRepo.findById(id).get();
        kund.setNamn(name);
        kund.setEmail(email);
        kund.setTelefonNr(phone);
        kund.setPersonummer(ssn);
        kundRepo.save(kund);
        return "redirect:/customer";


    }

    @RequestMapping(path ="/customer/deleteById/{id}")
    public String deleteById(@PathVariable Long id) {
        kundRepo.deleteById(id);
        return "redirect:/customer";

    }


}
