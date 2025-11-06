package com.reports.event_report.service.impl;

import com.reports.event_report.repository.EventRepository;
import com.reports.event_report.repository.entity.Event;
import com.reports.event_report.service.EventManager;
import com.reports.event_report.service.mapper.EventMapper;
import com.reports.event_report.web.dto.EventDTO;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class EventService implements EventManager {

    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDTO createEvent(@NotNull EventDTO eventDTO) {
        log.info("Adding event: {}", eventDTO);
        return eventMapper.toDTO(eventRepository.save(eventMapper.toEntity(eventDTO)));
    }

    @Override
    public List<EventDTO> searchByDateRange(@NotNull String fromDate, @NotNull String toDate) {

        LocalDateTime startDate;
        LocalDateTime endDate;

        try {
            startDate = LocalDateTime.parse(fromDate);
            endDate = LocalDateTime.parse(toDate);
        } catch (DateTimeParseException e) {
            log.error("Invalid date format: {} or {}", fromDate, toDate);
            throw new IllegalArgumentException(e.getMessage());

        }
        return eventRepository.findByDateBetween(startDate, endDate).stream()
                .map(eventMapper::toDTO)
                .toList();
    }

    @Override
    public List<EventDTO> getForLastDay(@NotNull String toDate) {

        LocalDateTime endDate;

        try {
            endDate = LocalDateTime.parse(toDate);
            return eventRepository.getForLastDay(endDate).stream()
                    .map(eventMapper::toDTO)
                    .toList();
        } catch (DateTimeParseException e) {
            log.error("Invalid date format: date={}", toDate);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<EventDTO> getForLastWeek(@NotNull String date) {
        try {
            return eventRepository.getForLastDay(LocalDateTime.parse(date)).stream()
                    .map(eventMapper::toDTO)
                    .toList();
        } catch (DateTimeParseException e) {
            log.error("Invalid date format: date={}", date);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public EventDTO updateEvent(@NotNull Long id, @NotNull EventDTO eventDTO) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("Event with id %d not found", id));

        }
        if (!id.equals(eventDTO.id())) {
            log.error("Event ID mismatch: path id {} and eventDTO id {}", id, eventDTO.id());
            throw new IllegalArgumentException(String.format("Event ID %d in path and in body %d do not match", id, eventDTO.id()));
        }
        log.info("Updating event with id {}: {}", id, eventDTO);
        return eventMapper.toDTO(eventRepository.save(eventMapper.toEntity(eventDTO)));
    }

    @Override
    public void removeEvent(@NotNull Long id) {
        if (!eventRepository.existsById(id)) {
            log.error("Event with id {} not found for deletion", id);
            throw new ResourceNotFoundException(String.format("Event with id %d not found", id));
        }
        log.info("Removing event with id {}", id);
        eventRepository.deleteById(id);
    }
}
