package org.example.bokningssystem.repo;

import org.example.bokningssystem.modell.ContractCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractCustomerRepo extends JpaRepository<ContractCustomer, Long> {
}
