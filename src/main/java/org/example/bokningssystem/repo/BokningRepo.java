package org.example.bokningssystem.repo;

import org.example.bokningssystem.modell.Bokning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BokningRepo extends JpaRepository<Bokning, Integer> {
}
