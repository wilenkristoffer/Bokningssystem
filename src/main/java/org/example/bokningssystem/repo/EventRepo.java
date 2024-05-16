package org.example.bokningssystem.repo;

import org.example.bokningssystem.modell.Event;
import org.example.bokningssystem.modell.Rum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findByRum(Rum rum);
}