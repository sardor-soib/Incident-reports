package com.reports.event_report.service;

import com.reports.event_report.web.dto.EventDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface EventManager {

    void addEvent(EventDTO eventDTO);

    List<EventDTO> searchByDateBetween(String fromDate, String toDate);

    List<EventDTO> getForLastDay(String date);

    List<EventDTO> getForLastWeek(String date);

    void updateEvent(Long id, EventDTO eventDTO);

    void removeEvent(Long id);
}
