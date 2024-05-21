package org.example.bokningssystem.repo;

import org.example.bokningssystem.dtos.*;
import org.example.bokningssystem.modell.Rum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EventRepo extends JpaRepository<RoomEvent, Long> {

    @Query("SELECT e FROM RoomClosedEvent e WHERE e.roomNo = :roomNo")
    List<RoomClosedEvent> findClosedEventsByRoomNo(@Param("roomNo") String roomNo);

    @Query("SELECT e FROM RoomOpenedEvent e WHERE e.roomNo = :roomNo")
    List<RoomOpenedEvent> findOpenedEventsByRoomNo(@Param("roomNo") String roomNo);

    @Query("SELECT e FROM RoomCleaningStartedEvent e WHERE e.roomNo = :roomNo")
    List<RoomCleaningStartedEvent> findCleaningStartedEventsByRoomNo(@Param("roomNo") String roomNo);

    @Query("SELECT e FROM RoomCleaningFinishedEvent e WHERE e.roomNo = :roomNo")
    List<RoomCleaningFinishedEvent> findCleaningFinishedEventsByRoomNo(@Param("roomNo") String roomNo);
}
