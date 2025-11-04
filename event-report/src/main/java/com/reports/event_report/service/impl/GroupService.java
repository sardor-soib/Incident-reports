package com.reports.event_report.service.impl;

import com.reports.event_report.repository.GroupRepository;
import com.reports.event_report.repository.entity.Group;
import com.reports.event_report.service.GroupManager;
import com.reports.event_report.service.mapper.GroupMapper;
import com.reports.event_report.web.dto.GroupDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GroupService implements GroupManager {

    Logger log = LoggerFactory.getLogger(GroupService.class);

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public void create(@NotNull @NotBlank String name) {
        if (groupRepository.existsByName(name)){
            log.error("Group with name {} already exists", name);
            throw new IllegalArgumentException(String.format("Group with name %s already exists", name));
        }
        groupRepository.save(new Group(null, name, new ArrayList<>()));
    }

    @Override
    public List<GroupDTO> search(@NotNull @NotBlank String name) {
        log.info("Searching groups with name containing: {}", name);
        return groupMapper.toDTOList(groupRepository.findByNameContainingIgnoreCase(name));
    }

    @Override
    public void update(@NotNull Long id, @NotNull GroupDTO groupDTO) {
        if (!groupRepository.existsById(id)){
            log.error("Group with id {} does not exist", id);
            throw new IllegalArgumentException(String.format("Group with id %d does not exist", id));
        }
        if (!id.equals(groupDTO.id())){
            log.error("Group id in path {} does not match id in body {}", id, groupDTO.id());
            throw new IllegalArgumentException(String.format("Group id in path %d does not match id in body %d", id, groupDTO.id()));
        }

        groupRepository.save(groupMapper.toEntity(groupDTO));
        log.info("Group with id {} updated successfully", id);
    }

    @Override
    public void delete(@NotNull Long id) {
        if (!groupRepository.existsById(id)){
            log.error("Group with id {} does not exist", id);
            throw new IllegalArgumentException(String.format("Group with id %d does not exist", id));
        }

        groupRepository.deleteById(id);
        log.info("Group with id {} deleted successfully", id);
    }
}
