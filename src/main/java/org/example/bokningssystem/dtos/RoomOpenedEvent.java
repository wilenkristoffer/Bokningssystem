package org.example.bokningssystem.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_opened_events")
public class RoomOpenedEvent extends RoomEvent {

    @Getter
    private final String description = "Dörren öppnad";
    public RoomOpenedEvent() {

    }
}
