package org.example.bokningssystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlacklistController {

    @RequestMapping("blacklist")
    public String blacklist() {
        return "blacklist";
    }
}
