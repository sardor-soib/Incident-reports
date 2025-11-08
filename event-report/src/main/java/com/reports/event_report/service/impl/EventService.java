package com.reports.event_report.service.impl;

import com.reports.event_report.repository.EventRepository;
import com.reports.event_report.service.EventManager;
import com.reports.event_report.service.mapper.EventMapper;
import com.reports.event_report.web.dto.EventDTO;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
    public Page<EventDTO> searchByDateRange(@NotNull String fromDate, @NotNull String toDate, @NotNull Pageable pageable) {

        LocalDateTime startDate;
        LocalDateTime endDate;

        try {
            startDate = LocalDateTime.parse(fromDate);
            endDate = LocalDateTime.parse(toDate);
        } catch (DateTimeParseException e) {
            log.error("Invalid date format: {} or {}", fromDate, toDate);
            throw new IllegalArgumentException(e.getMessage());

        }
        return eventMapper.toDTOPage(eventRepository.findByStartTimeBetween(startDate, endDate, pageable));
    }

    @Override
    public Page<EventDTO> getForLastDay(@NotNull Pageable pageable) {
        log.info("Fetching events for the last day");
        return eventMapper.toDTOPage(eventRepository.findByStartTimeBetween(LocalDateTime.now().minusDays(1), LocalDateTime.now(), pageable));
    }

    @Override
    public Page<EventDTO> getForLastWeek(@NotNull Pageable pageable) {
        log.info("Fetching events for the last week");
        return eventMapper.toDTOPage(eventRepository.findByStartTimeBetween(LocalDateTime.now().minusWeeks(1), LocalDateTime.now(), pageable));

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
