package org.example.bokningssystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedKundDto;
import org.example.bokningssystem.security.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;


    @RequestMapping("/user")
    public String userAccounts(Model model) {
        List<User> users = userDetailsService.getAllUsers();
        model.addAttribute("user", new User());
        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "Alla befintliga användare");
        model.addAttribute("tableHeadings", Arrays.asList("Namn", "Role"));
        model.addAttribute("emptyListMessage", "Inga användare hittades");
        return "userAccounts.html";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable UUID id) {
        userDetailsService.deleteUserById(id);
        return "redirect:/user";
    }
}
