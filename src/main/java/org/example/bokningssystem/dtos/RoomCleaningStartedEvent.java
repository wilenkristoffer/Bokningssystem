package org.example.bokningssystem.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_cleaning_started_events")
public class RoomCleaningStartedEvent extends RoomEvent {
    @Getter @Setter
    @JsonProperty("CleaningByUser")
    private String cleaningByUser;



    public RoomCleaningStartedEvent() {}

    public String getDescription() {
        return "Städning påbörjat av " + cleaningByUser;
    }

}
