package com.reports.event_report.service;

import com.reports.event_report.web.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface EventManager {

    EventDTO createEvent(EventDTO eventDTO);

    Page<EventDTO> searchByDateRange(String fromDate, String toDate, Pageable pageable);

    Page<EventDTO> getForLastDay(Pageable pageable);

    Page<EventDTO> getForLastWeek(Pageable pageable);

    EventDTO updateEvent(Long id, EventDTO eventDTO);

    void removeEvent(Long id);
}
