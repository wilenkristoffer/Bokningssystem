package org.example.bokningssystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.ContractCustomerDto;
import org.example.bokningssystem.dtos.DetailedContractCustomerDto;
import org.example.bokningssystem.modell.ContractCustomer;
import org.example.bokningssystem.repo.ContractCustomerRepo;
import org.example.bokningssystem.services.ContractCustomerService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public DetailedContractCustomerDto contractCustomerToDetailedContractCustomerDto(ContractCustomer c) {
        return DetailedContractCustomerDto.builder().id(c.getId()).companyName(c.getCompanyName())
                .contactName(c.getContactName()).contactTitle(c.getContactTitle()).streetAddress(c.getStreetAddress())
                .city(c.getCity()).postalCode(c.getPostalCode()).country(c.getCountry()).phone(c.getPhone())
                .fax(c.getFax()).build();

    }

    @Override
    public Page<ContractCustomerDto> getAllCustomersSimple(Pageable pageable) {
        Page<ContractCustomer> customers = contractCustomerRepo.findAll(pageable);
        return customers.map(this::contractCustomerToContractCustomerDto);
    }

    @Override
    public DetailedContractCustomerDto getCustomerById(Long customerId) {
        Optional<ContractCustomer> customerOptional = contractCustomerRepo.findById(customerId);
        ContractCustomer customer = customerOptional.get();
        return contractCustomerToDetailedContractCustomerDto(customer);
    }

    @Override
    public Page<ContractCustomerDto> getCustomers(String search, Pageable pageable) {
        if (search == null) {
            return getAllCustomersSimple(pageable);
        } else {

            return contractCustomerRepo.findByCompanyNameContainingIgnoreCaseOrContactNameContainingIgnoreCaseOrCountryContainingIgnoreCase(
                            search, search, search, pageable)
                    .map(this::contractCustomerToContractCustomerDto);
        }

}
    @Override
    public Page<ContractCustomerDto> getContractCustomers(int page, int size, String sortBy, String direction, String search) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        if (search != null && !search.isEmpty()) {
            return contractCustomerRepo.findByCompanyNameContainingIgnoreCaseOrContactNameContainingIgnoreCaseOrCountryContainingIgnoreCase(
                            search, search, search, pageable)
                    .map(this::contractCustomerToContractCustomerDto);
        } else {
            return contractCustomerRepo.findAll(pageable)
                    .map(this::contractCustomerToContractCustomerDto);
        }
    }
}
