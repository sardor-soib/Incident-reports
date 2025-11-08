package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.Event;
import com.reports.event_report.repository.entity.Group;
import com.reports.event_report.web.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "groups", expression = "java(map(eventDTO.groupIds()))")
    @Mapping(target = "region.id", source = "regionId")
    @Mapping(target = "functionality.id", source = "functionalityId")
    Event toEntity(EventDTO eventDTO);

    @Mapping(target = "groupIds", expression = "java(mapGroupsToIds(event.getGroups()))")
    @Mapping(target = "regionId", source = "region.id")
    @Mapping(target = "functionalityId", source = "functionality.id")
    EventDTO toDTO(Event event);

    List<Event> toEntityList(List<EventDTO> eventDTOs);

    List<EventDTO> toDTOList(List<Event> events);

    default Page<EventDTO> toDTOPage(Page<Event> page) {
        List<EventDTO> dtoList = toDTOList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default List<Group> map(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Group g = new Group();
            g.setId(id);
            return g;
        }).collect(Collectors.toList());
    }

    default List<Long> mapGroupsToIds(List<Group> groups) {
        if (groups == null) return null;
        return groups.stream()
                .map(Group::getId)
                .collect(Collectors.toList());
    }
}
