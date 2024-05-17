package org.example.bokningssystem.services;

import org.example.bokningssystem.dtos.DetailedEventDto;
import org.example.bokningssystem.dtos.DetailedRumDto;
import org.example.bokningssystem.modell.Event;
import org.example.bokningssystem.modell.Rum;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> getAllEvents();
    Optional<Event> getEventById(Long id);
    Event createEvent(Long rumID, Event event);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);

    List<Event> getAllEventsForRum(Rum rum);
}
