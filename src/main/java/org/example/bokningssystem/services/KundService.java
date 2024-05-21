package org.example.bokningssystem.services;

import org.example.bokningssystem.dtos.DetailedKundDto;
import org.example.bokningssystem.dtos.KundDto;
import org.example.bokningssystem.modell.Kund;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface KundService {

    public KundDto kundToKundDto(Kund k);
    public DetailedKundDto kundToDetailedKundDto(Kund k);
    public Kund detailedKundToDetailedKundDto(DetailedKundDto k);
    public List<DetailedKundDto> getAllKunder();
    public List<KundDto> getAllKundSimple();

    public String addKund(DetailedKundDto kund);
    public String modifyKund(DetailedKundDto updatedKund);
    public String deleteCustomerCheck(Long customerId);
    public String deleteCustomer(Long customerId);
    public void updateNightsLastYearForAllCustomers();
}
