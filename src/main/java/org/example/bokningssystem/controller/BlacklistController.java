package org.example.bokningssystem.controller;

import org.example.bokningssystem.services.impl.BlackListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;

@Controller
public class BlacklistController {

    @RequestMapping("blacklist")
    public String blacklist() {
        return "blacklist";
    }

    @Autowired
    private BlackListServiceImpl blackListService;

    @PostMapping("/addEmail")
    public String addEmailToBlacklist(@RequestParam("email") String email, Model model) {
        try {
            blackListService.addEmailToBlackList(email);
            model.addAttribute("message", "Email added to blacklist successfully.");
        } catch (URISyntaxException e) {
            model.addAttribute("message", "Error adding email to blacklist: " + e.getMessage());
            e.printStackTrace();
        }
        return "blacklist.html";
    }
}
