package com.reports.event_report.repository;

import com.reports.event_report.repository.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);

}
