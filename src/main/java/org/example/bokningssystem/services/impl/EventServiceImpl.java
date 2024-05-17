package org.example.bokningssystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.bokningssystem.dtos.DetailedEventDto;
import org.example.bokningssystem.dtos.DetailedRumDto;
import org.example.bokningssystem.modell.Event;
import org.example.bokningssystem.modell.Rum;
import org.example.bokningssystem.repo.EventRepo;
import org.example.bokningssystem.repo.RumRepo;
import org.example.bokningssystem.services.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;

    private final RumRepo rumRepo;


    @Override
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepo.findById(id);
    }

    @Override
    public Event createEvent(Long rumId,Event event) {
        Optional<Rum> rumOptional =rumRepo.findById(rumId);
        if (rumOptional.isPresent()) {
            Rum rum = rumOptional.get();
            event.setRum(rum);
            return eventRepo.save(event);
        } else {
            return null;
        }
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        if (eventRepo.existsById(id)) {
            event.setId(id);
            return eventRepo.save(event);
        }
        return null;
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);

    }


    @Override
    public List<Event> getAllEventsForRum(Rum rum) {
        return eventRepo.findByRum(rum);
    }



}
