package com.reports.event_report.repository;

import com.reports.event_report.repository.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDateBetween(LocalDate fromDate, LocalDate toDate);

    List<Event> getForLastDay(LocalDate date);

    List<Event> getForLastWeek(LocalDate date);

}
