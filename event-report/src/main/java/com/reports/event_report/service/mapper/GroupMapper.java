package com.reports.event_report.service.mapper;

import com.reports.event_report.repository.entity.Group;
import com.reports.event_report.web.dto.GroupDTO;

import java.util.List;

public interface GroupMapper {

    Group toEntity(GroupDTO groupDTO);

    GroupMapper toDTO(Group group);

    List<Group> toEntityList(List<GroupDTO> groupDTOs);

    List<GroupDTO> toDTOList(List<Group> groups);
}
