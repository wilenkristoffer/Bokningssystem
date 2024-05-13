package org.example.bokningssystem.services;

import org.example.bokningssystem.dtos.ContractCustomerDto;
import org.example.bokningssystem.dtos.DetailedContractCustomerDto;
import org.example.bokningssystem.modell.ContractCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ContractCustomerService {



    public ContractCustomerDto contractCustomerToContractCustomerDto(ContractCustomer c);
    public DetailedContractCustomerDto contractCustomerToDetailedContractCustomerDto(ContractCustomer c);

    public Page<ContractCustomerDto> getAllCustomersSimple(Pageable pageable);
    public DetailedContractCustomerDto getCustomerById(Long customerId);
    public Page<ContractCustomerDto> getCustomers(String search, Pageable pageable);
    public Page<ContractCustomerDto> getContractCustomers(int page, int size, String sortBy, String direction, String search);
}
