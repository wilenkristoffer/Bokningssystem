package org.example.bokningssystem.repo;

import org.example.bokningssystem.modell.Rum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RumRepo extends JpaRepository<Rum, Integer> {
}
