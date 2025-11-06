package com.reports.event_report.service;

import com.reports.event_report.web.dto.EventDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EventManager {

    EventDTO createEvent(EventDTO eventDTO);

    List<EventDTO> searchByDateRange(String fromDate, String toDate);

    List<EventDTO> getForLastDay(String date);

    List<EventDTO> getForLastWeek(String date);

    EventDTO updateEvent(Long id, EventDTO eventDTO);

    void removeEvent(Long id);
}
