package org.example.bokningssystem.services;

import org.example.bokningssystem.dtos.ContractCustomerDto;
import org.example.bokningssystem.dtos.DetailedContractCustomerDto;
import org.example.bokningssystem.modell.ContractCustomer;


import java.util.List;

public interface ContractCustomerService {



    ContractCustomerDto contractCustomerToContractCustomerDto(ContractCustomer c);

    public List<ContractCustomerDto> getAllCustomersSimple();

    public DetailedContractCustomerDto contractCustomerToDetailedContractCustomerDto(ContractCustomer c);

    public List<DetailedContractCustomerDto> getAllCustomers();

    DetailedContractCustomerDto getCustomerById(Long customerId);
}
