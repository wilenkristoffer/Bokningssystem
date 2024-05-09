package org.example.bokningssystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.ContractCustomerDto;
import org.example.bokningssystem.services.ContractCustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContractCustomerController {

    private final ContractCustomerService contractCustomerService;

    @RequestMapping("contractCustomers")
        public String contractCustomers(Model model){

        List<ContractCustomerDto> contractCustomers = contractCustomerService.getAllCustomersSimple();

        model.addAttribute("contractCustomers", contractCustomers);
        model.addAttribute("emptyListMessage", "Inga företagskunder hittades");
            return "contractCustomers.html";
        }
}

