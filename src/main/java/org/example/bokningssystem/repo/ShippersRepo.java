package org.example.bokningssystem.repo;

import org.example.bokningssystem.modell.Shippers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippersRepo extends JpaRepository<Shippers, Long> {
}
