package org.example.bokningssystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.ContractCustomerDto;
import org.example.bokningssystem.dtos.DetailedContractCustomerDto;

import org.example.bokningssystem.services.ContractCustomerService;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            return "contractCustomers";
        }
    @GetMapping("/allDetails")
    public String getCustomerDetails(Long customerId, RedirectAttributes redirectAttributes) {

        DetailedContractCustomerDto customer = contractCustomerService.getCustomerById(customerId);

        redirectAttributes.addFlashAttribute("customerAllinfo", customer);

        return "redirect:/customerDetails";
    }
    @RequestMapping("/customerDetails")
    public String customerDetails(){
        return "customerDetails";

    }
    @GetMapping("/customers")
    public ResponseEntity<List<ContractCustomerDto>> getCustomers(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String search
    ) {
        List<ContractCustomerDto> customers = contractCustomerService.getCustomers(sortBy, search);
        return ResponseEntity.ok(customers);
    }
}

