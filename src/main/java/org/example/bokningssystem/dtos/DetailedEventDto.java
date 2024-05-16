package org.example.bokningssystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedEventDto {
    private Long id;
    private boolean isDoorOpen;
    private boolean isRoomCleaned;
    private LocalDateTime timestamp;
    private String description;
}
