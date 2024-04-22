package org.example.bokningssystem.repo;

import org.example.bokningssystem.modell.Kund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundRepo extends JpaRepository<Kund,Long> {
}
