package com.reports.event_report.web.controller;

import com.reports.event_report.service.EventManager;
import com.reports.event_report.web.dto.EventDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@Tag(name = "Event Controller", description = "APIs for managing events")
@RequestMapping("/api/event")
public class EventController {

    private final EventManager eventManager;

    @Autowired
    public EventController(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Operation(summary = "Create a new event", description = "Creates a new event with the provided details")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EventDTO createEvent(@RequestBody @Valid EventDTO eventDTO) {
        return eventManager.createEvent(eventDTO);
    }

    @Operation(summary = "Search events by date range", description = "Retrieves events that fall within the specified date range")
    @GetMapping("/search")
    public List<EventDTO> searchByDateRange(@RequestParam("from") String fromDate, @RequestParam("to") String toDate) {
        return eventManager.searchByDateRange(fromDate, toDate);
    }

    @Operation(summary = "Get events for a specific day", description = "Retrieves all events for the specified day")
    @GetMapping("/by-day")
    public List<EventDTO> getForLastDay(@RequestParam("date") String date) {
        return eventManager.getForLastDay(date);
    }

    @Operation(summary = "Get events for a specific week", description = "Retrieves all events for the week containing the specified date")
    @GetMapping("/by-week")
    public List<EventDTO> getForLastWeek(@RequestParam("date") String date) {
        return eventManager.getForLastWeek(date);
    }

    @Operation(summary = "Update an existing event", description = "Updates the details of an existing event identified by its ID")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Event details to update", required = true)
    @PutMapping("/{id}")
    public EventDTO updateEvent(@PathVariable Long id, @RequestBody @Valid EventDTO eventDTO) {
        return eventManager.updateEvent(id, eventDTO);
    }

    @Operation(summary = "Delete an event", description = "Deletes the event identified by its ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventManager.removeEvent(id);
    }
}
