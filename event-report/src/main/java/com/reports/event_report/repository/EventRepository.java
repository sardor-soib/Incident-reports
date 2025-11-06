package com.reports.event_report.repository;

import com.reports.event_report.repository.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.startTime BETWEEN :from AND :to")
    List<Event> findByDateBetween(LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT e FROM Event e WHERE e.startTime >= :start AND e.startTime < :end")
    List<Event> getForLastWeek(LocalDateTime endTime);

    @Query("SELECT e FROM Event e WHERE e.startTime >= :start AND e.startTime < :end")
    List<Event> getForLastDay(LocalDateTime endTime);

}
