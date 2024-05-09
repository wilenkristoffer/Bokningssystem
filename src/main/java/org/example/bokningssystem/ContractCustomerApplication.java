package org.example.bokningssystem;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.ContractCustomerDto;
import org.example.bokningssystem.modell.AllCustomers;
import org.example.bokningssystem.modell.ContractCustomer;
import org.example.bokningssystem.repo.ContractCustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.net.URL;

@ComponentScan
public class ContractCustomerApplication implements CommandLineRunner {

    ContractCustomerRepo customerRepo;

    public ContractCustomerApplication(ContractCustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper mapper = new XmlMapper(module);
        AllCustomers customers =mapper.readValue(new URL("https://javaintegration.systementor.se/customers"),
                AllCustomers.class);


            for (ContractCustomer c : customers.customers) {
                customerRepo.save(c);
            }
    }
}
