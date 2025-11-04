package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.Event;
import com.reports.event_report.web.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "responsibleGroups", source = "responsibleGroupIds")
    @Mapping(target = "region.id", source = "regionId")
    @Mapping(target = "functionality.id", source = "functionalityId")
    Event toEntity(EventDTO eventDTO);

    @Mapping(target = "responsibleGroupIds", source = "responsibleGroups")
    @Mapping(target = "regionId", source = "region.id")
    @Mapping(target = "functionalityId", source = "functionality.id")
    EventDTO toDTO(Event event);

    List<Event> toEntityList(List<EventDTO> eventDTOs);

    List<EventDTO> toDTOList(List<Event> events);
}
