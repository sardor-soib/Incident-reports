package com.reports.event_report.service.impl;

import com.reports.event_report.repository.GroupRepository;
import com.reports.event_report.repository.entity.Group;
import com.reports.event_report.service.GroupManager;
import com.reports.event_report.service.mapper.GroupMapper;
import com.reports.event_report.web.dto.GroupDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupService implements GroupManager {

    private final Logger log = LoggerFactory.getLogger(GroupService.class);

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupDTO createGroup(@NotNull @Valid GroupDTO groupDTO) {
        if (groupRepository.existsByName(groupDTO.name())) {
            log.error("Group with name {} already exists", groupDTO.name());
            throw new IllegalArgumentException(String.format("Group with name %s already exists", groupDTO.name()));
        }
        return groupMapper.toDTO(groupRepository.save(new Group(null, groupDTO.name(), new ArrayList<>())));
    }

    @Override
    public Page<GroupDTO> search(@NotNull @NotBlank String name, @NotNull Pageable pageable) {
        log.info("Searching groups with name containing: {}", name);
        return groupMapper.toDTOPage(groupRepository.findByNameContainingIgnoreCase(name, pageable));
    }

    @Override
    public GroupDTO updateGroup(@NotNull Long id, @NotNull GroupDTO groupDTO) {
        if (!groupRepository.existsById(id)) {
            log.error("Group with id {} does not exist", id);
            throw new IllegalArgumentException(String.format("Group with id %d does not exist", id));
        }
        if (!id.equals(groupDTO.id())) {
            log.error("Group id in path {} does not match id in body {}", id, groupDTO.id());
            throw new IllegalArgumentException(String.format("Group id in path %d does not match id in body %d", id, groupDTO.id()));
        }
        return groupMapper.toDTO(groupRepository.save(groupMapper.toEntity(groupDTO)));
    }

    @Override
    public void deleteGroup(@NotNull Long id) {
        if (!groupRepository.existsById(id)) {
            log.error("Group with id {} does not exist", id);
            throw new IllegalArgumentException(String.format("Group with id %d does not exist", id));
        }
        groupRepository.deleteById(id);
        log.info("Group with id {} deleted successfully", id);
    }
}
