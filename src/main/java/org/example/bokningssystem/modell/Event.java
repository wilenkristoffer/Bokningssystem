package org.example.bokningssystem.modell;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isDoorOpen;
    private boolean isRoomCleaned;
    private LocalDateTime timestamp;
    private String description;


    @ManyToOne
    @JoinColumn(name = "rum_id", nullable = false)
    private Rum rum;
}
