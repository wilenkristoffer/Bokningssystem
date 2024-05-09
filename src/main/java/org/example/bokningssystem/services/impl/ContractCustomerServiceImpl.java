package org.example.bokningssystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.ContractCustomerDto;
import org.example.bokningssystem.dtos.DetailedContractCustomerDto;
import org.example.bokningssystem.modell.ContractCustomer;
import org.example.bokningssystem.repo.ContractCustomerRepo;
import org.example.bokningssystem.services.ContractCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractCustomerServiceImpl implements ContractCustomerService {

    private final ContractCustomerRepo contractCustomerRepo;




    @Override
    public ContractCustomerDto contractCustomerToContractCustomerDto(ContractCustomer c) {
        return ContractCustomerDto.builder().id(c.getId())
                .companyName(c.getCompanyName())
                .contactName(c.getContactName())
                .country(c.getCountry()).build();

    }


    @Override
    public List<ContractCustomerDto> getAllCustomersSimple() {
        return contractCustomerRepo.findAll().stream()
                .map(this::contractCustomerToContractCustomerDto).toList();
    }

    @Override
    public DetailedContractCustomerDto contractCustomerToDetailedContractCustomerDto(ContractCustomer c) {
        return DetailedContractCustomerDto.builder().id(c.getId()).companyName(c.getCompanyName())
                .contactName(c.getContactName()).contactTitle(c.getContactTitle()).streetAddress(c.getStreetAddress())
                .city(c.getCity()).postalCode(c.getPostalCode()).country(c.getCountry()).phone(c.getPhone())
                .fax(c.getFax()).build();

    }

    @Override
    public List<DetailedContractCustomerDto> getAllCustomers() {
        return contractCustomerRepo.findAll().stream().map(this::contractCustomerToDetailedContractCustomerDto).toList();
    }
}
